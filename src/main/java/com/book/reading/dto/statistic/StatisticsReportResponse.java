package com.book.reading.dto.statistic;

import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticsReportResponse implements Serializable {

    String orderedDate;
    Integer totalRangeCount;
    Integer totalBookCount;
    Double totalPurchaseAmount;

}
