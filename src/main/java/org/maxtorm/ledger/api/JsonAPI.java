package org.maxtorm.ledger.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class JsonAPI {
    private static final Logger logger = LoggerFactory.getLogger(JsonAPI.class);
    protected <T> T perform(HttpRequest request, Class<T> valueType) throws APIFail {
        logger.debug("request: {}", request);
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.debug("response: {}", response);

            if (response.statusCode() != 200) {
                throw new APIFail("statusCode", response.statusCode());
            }

            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            return objectMapper.readValue(response.body(), valueType);
        } catch (IOException | InterruptedException e) {
            throw new APIFail(String.format("exception: %s", e.getMessage()));
        }
    }

    private HttpClient httpClient = HttpClient.newBuilder().build();
}
