package org.maxtorm.ledger.commodity.exception;

import lombok.Getter;
import org.maxtorm.ledger.commodity.Commodity;
import org.slf4j.helpers.MessageFormatter;

import java.util.ArrayList;
import java.util.List;

public class CommodityProviderNetworkFailException extends Exception {
    @Getter
    private final List<Commodity> relatedCommodityList;

    public CommodityProviderNetworkFailException(Commodity commodity1, String format, Object... arguments) {
        super(MessageFormatter.format(format, arguments).getMessage());
        relatedCommodityList = new ArrayList<>();
        relatedCommodityList.add(commodity1);
    }

    public CommodityProviderNetworkFailException(Commodity commodity1, Commodity commodity2, String format, Object... arguments) {
        super(MessageFormatter.format(format, arguments).getMessage());
        relatedCommodityList = new ArrayList<>();
        relatedCommodityList.add(commodity1);
        relatedCommodityList.add(commodity2);
    }
}
