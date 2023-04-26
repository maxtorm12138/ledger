package org.maxtorm.ledger.entity.commodity.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxtorm.ledger.entity.commodity.Fund;
import org.maxtorm.ledger.entity.commodity.exception.CommodityProviderNetworkFailException;
import org.springframework.data.util.Pair;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class EasyMoneyProvider implements IFundProvider {
    private static final HttpClient httpClient = HttpClient.newBuilder().build();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String host = "http://easymoneyapi:3000";
    @Override
    public Pair<LocalDate, BigDecimal> getFundNav(Fund fund) throws CommodityProviderNetworkFailException {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(host).path("/fundVarietieValuationDetail").queryParam("FCODE", fund.getCode()).build().toUri();
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var data = objectMapper.readTree(response.body()).get("Expansion");
            LocalDate localDate = LocalDate.parse(data.get("JZRQ").asText());
            BigDecimal nav = new BigDecimal(data.get("DWJZ").asText());
            return Pair.of(localDate, nav);

        } catch (IOException | InterruptedException e) {
            throw new CommodityProviderNetworkFailException(fund, "network fail {}", e.getMessage());
        }
    }
}
