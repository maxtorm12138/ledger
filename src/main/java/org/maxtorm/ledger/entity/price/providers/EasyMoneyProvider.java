package org.maxtorm.ledger.entity.price.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.maxtorm.ledger.entity.commodity.Commodity;
import org.maxtorm.ledger.entity.price.Price;
import org.maxtorm.ledger.entity.price.QuoteProvider;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@AllArgsConstructor
public class EasyMoneyProvider implements QuoteProvider {
    private static final HttpClient httpClient = HttpClient.newBuilder().build();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private String easyMoneyHost;

    @Override
    public boolean isSupport(Commodity commodity, Commodity currency) {
        return commodity.getNamespace().startsWith("fund::china") && currency.equals("currency::CNY");
    }

    @Override
    public String getName() {
        return "quote::provider::fund::eastmoney";
    }

    @Override
    public Price getQuote(Commodity commodity, Commodity currency) {
        assert currency.equals("currency::CNY");
        try {

            URI uri = UriComponentsBuilder.fromHttpUrl(easyMoneyHost).path("/fundVarietieValuationDetail").queryParam("FCODE", commodity.getMnemonic()).build().toUri();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var data = objectMapper.readTree(response.body()).get("Expansion");
            BigDecimal nav = new BigDecimal(data.get("DWJZ").asText());
            return Price.builder().source(getName()).type("nav").value(nav.unscaledValue()).valueDenominator(BigInteger.TEN.pow(nav.scale())).currency(currency).commodity(commodity).build();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}