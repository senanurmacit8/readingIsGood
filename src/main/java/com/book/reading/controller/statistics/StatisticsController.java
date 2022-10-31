package com.book.reading.controller.statistics;

import com.book.reading.dto.statistic.StatisticsReportResponse;
import com.book.reading.service.logic.StatisticsLogicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author senanurmacit
 * @version 1.1
 * @since 1.1
 */

@RestController
public class StatisticsController {

    private StatisticsLogicService service;

    public StatisticsController(StatisticsLogicService service) {
        this.service = service;
    }

    /**
     * This method was created for providing
     * <p>
     * • Will serve customer’s monthly order statistics
     * • Total Order count
     * • Total amount of all purchased orders
     * • Total count of purchased books
     * That endpoint will supply data for the following UIwas  component
     * Month Total Order Count Total Book Count Total Purchased Amount
     * May 2 5 155.43
     * April 1 3 55.2
     *
     * @param customerId
     * @return StatisticsReportResponse
     * @see StatisticsReportResponse
     */
    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    public List<StatisticsReportResponse> getStatisticsMonthlyByCustomerIdAndDateRange(String customerId) {
        return service.getStatisticsReportInfo(customerId);

    }

}
