package org.maxtorm.ledger.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.maxtorm.ledger.error.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class JsonAPI {

    protected abstract Logger getLogger();

    protected <T> T perform(HttpRequest request, Class<T> valueType) {
        try {
            getLogger().debug("request: {}", request);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            getLogger().debug("response: {}", response);

            if (response.statusCode() != 200) {
                getLogger().error("invalid response status code: {}", response.statusCode());
                throw new APIError(ErrorCode.NetworkError, "invalid response status code: {}", response.statusCode());
            }

            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            return objectMapper.readValue(response.body(), valueType);
        } catch (IOException | InterruptedException e) {
            getLogger().error("system error: {}", e.getMessage());
            throw new APIError(ErrorCode.NetworkError, "system error: {}", e.getMessage());
        }
    }

    private HttpClient httpClient = HttpClient.newBuilder().build();
}
