package com.book.reading.service.logic;

import com.book.reading.dto.statistic.StatisticsReportResponse;

import java.util.List;

public interface StatisticsLogicService {

    List<StatisticsReportResponse> getStatisticsReportInfo(String customerId);

}
