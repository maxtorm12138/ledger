package org.maxtorm.ledger.broker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.maxtorm.ledger.bo.Commodity;
import org.maxtorm.ledger.broker.exception.ConvertExchangeFailed;
import org.maxtorm.ledger.broker.provider.ForeignExchangeProvider;
import org.maxtorm.ledger.util.LedgerDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class CoinMarketCapProvider implements ForeignExchangeProvider {
    private static final Logger logger = LoggerFactory.getLogger(CoinMarketCapProvider.class);
    private static final String path = "/v2/tools/price-conversion";

    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @Getter
    @Setter
    private String host = "https://pro-api.coinmarketcap.com";

    @NonNull
    private String apiToken;

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class Status {
        private ZonedDateTime timestamp;
        private Integer errorCode;
        private String errorMessage;
        private Integer elapsed;
        private Integer creditCount;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class ConvertQuote {
        private BigDecimal price;
        private ZonedDateTime lastUpdated;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class ConvertResult {
        private Integer id;
        private String symbol;
        private String name;
        private BigDecimal amount;
        private ZonedDateTime lastUpdated;
        private Map<String, ConvertQuote> quote;
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class ConvertExchangeResponse {
        private Status status;
        private List<ConvertResult> data;
    }

    @Override
    public BigDecimal convertExchange(Commodity source, BigDecimal amount, Commodity destination) throws ConvertExchangeFailed {
        if (source.getCategory() != Commodity.Category.Currency) {
            throw new ConvertExchangeFailed(source, destination, "source is not currency");
        }

        if (destination.getCategory() != Commodity.Category.Currency) {
            throw new ConvertExchangeFailed(source, destination, "destination is not currency");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ConvertExchangeFailed(source, destination, "amount is invalid: {}", amount);
        }

        amount = amount.setScale(5, LedgerDecimal.MATH_CONTEXT.getRoundingMode());

        var uri = UriComponentsBuilder
                .fromHttpUrl(host)
                .path(path)
                .queryParam("amount", amount.toString())
                .queryParam("symbol", source.getName())
                .queryParam("convert", destination.getName())
                .build()
                .toUri();

        HttpRequest httpRequest = HttpRequest.newBuilder().header("X-CMC_PRO_API_KEY", apiToken).uri(uri).build();
        HttpResponse<String> httpResponse;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            logger.error("http request failed: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        ConvertExchangeResponse response;
        try {
            response = objectMapper.readValue(httpResponse.body(), ConvertExchangeResponse.class);
        } catch (JsonProcessingException e) {
            throw new ConvertExchangeFailed(source, destination, "http response to json object failed, message: {}", e.getMessage());
        }

        if (response.getStatus().getErrorCode() != 0) {
            throw new ConvertExchangeFailed(source, destination, "business logic failed errorCode: {}, errorMessage: {}", response.getStatus().getErrorCode(), response.getStatus().getErrorMessage());
        }

        return response.getData().get(0).getQuote().get(destination.getName()).getPrice();
    }
}
