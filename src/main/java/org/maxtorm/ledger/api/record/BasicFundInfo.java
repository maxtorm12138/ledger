package org.maxtorm.ledger.api.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public record BasicFundInfo(
        String code,
        String name,
        BigDecimal netWorth,
        BigDecimal expectWorth,
        BigDecimal totalWorth,
        BigDecimal expectGrowth,
        BigDecimal dayGrowth,
        BigDecimal lastWeekGrowth,
        BigDecimal lastMonthGrowth,
        BigDecimal lastThreeMonthsGrowth,
        BigDecimal lastSixMonthsGrowth,
        BigDecimal lastYearGrowth,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date netWorthDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        Date expectWorthDate
) {
}
