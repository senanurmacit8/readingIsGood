package com.book.reading.dto.statistic;

import lombok.Data;

@Data
public class StatisticsInfo {

    private String orderCount;
    private String totalBookCount;
    private String totalPurchasedAmount;
    private String month;
}
