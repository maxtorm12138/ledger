package org.maxtorm.ledger.api;

import org.maxtorm.ledger.api.record.BasicFundInfo;
import org.maxtorm.ledger.util.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;


public class FundAPI extends JsonAPI {
    private record BasicFundInfoResponse(
            Integer code,
            String message,
            ArrayList<BasicFundInfo> data,
            String meta,
            boolean success
    ) {
    }

    public FundAPI(String token) {
        this.token = token;
    }

    public BasicFundInfo getBasicInfo(String code) {
        ArrayList<String> codes = new ArrayList<>();
        codes.add(code);
        ArrayList<BasicFundInfo> fundInfos = getBasicInfo(codes);

        if (fundInfos.isEmpty()) {
            logger.warn("no such fund: {}", code);
            throw new APIError(ErrorCode.NoSuchElement, "no such fund: {}", code);
        }

        if (fundInfos.size() > 1) {
            logger.error("invalid fund size: {}", fundInfos.size());
            throw new APIError(ErrorCode.LogicError, "invalid fund size: {]", fundInfos.size());
        }

        return fundInfos.get(0);
    }

    public ArrayList<BasicFundInfo> getBasicInfo(ArrayList<String> codes) {
        URI uri = UriComponentsBuilder.fromUriString("https://api.doctorxiong.club/v1/fund").queryParam("code", codes).build().toUri();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("token", token)
                .GET()
                .build();

        BasicFundInfoResponse response = perform(request, BasicFundInfoResponse.class);

        if (response.code != 200) {
            logger.error("getBasicInfo logic error, code: {} message: {}", response.code, response.message);
            throw new APIError(ErrorCode.LogicError, "getBasicInfo logic error, code: {} message: {}", response.code, response.message);
        }

        return response.data;
    }

    protected Logger getLogger() {
        return logger;
    }

    private final String token;

    private static final Logger logger = LoggerFactory.getLogger(FundAPI.class);
}
