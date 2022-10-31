package com.book.reading.service.logic.impl;

import com.book.reading.dto.constants.MonthsEnum;
import com.book.reading.dto.order.OrderInfo;
import com.book.reading.dto.statistic.StatisticsReportResponse;
import com.book.reading.service.book.BookService;
import com.book.reading.service.logic.StatisticsLogicService;
import com.book.reading.service.order.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author senanurmacit
 * @version 1.1
 * @since Version 1.1
 */
@Service
@Log4j2
public class StatisticsLogicServiceImpl implements StatisticsLogicService {

    private OrderService orderService;

    private BookService bookService;

    public StatisticsLogicServiceImpl(OrderService orderService,
                                      BookService bookService) {
        this.orderService = orderService;
        this.bookService = bookService;
    }


    /**
     * This method created for return table data
     * That endpoint will supply data for the following ui component
     *
     * @param customerId
     * @return List<StatisticsReportResponse>
     * @see StatisticsReportResponse
     */
    public List<StatisticsReportResponse> getStatisticsReportInfo(String customerId) {
        List<OrderInfo> sortedOrderInfoListByDate = this.listSortedOrderInfoByOrDate(customerId);
        List<StatisticsReportResponse> statisticsReportResponseList = null;

        if (!CollectionUtils.isEmpty(sortedOrderInfoListByDate)) {

            Arrays.stream(MonthsEnum.values()).map(monthsEnum -> {
                StatisticsReportResponse statisticsReportResponse = new StatisticsReportResponse();

                List<OrderInfo> orderInfoListByMonth = sortedOrderInfoListByDate.stream()
                        .filter(orderDTO -> monthsEnum.toString().equals(orderDTO.getOrderDate().getMonth().toString()))
                        .collect(Collectors.toList());

                long totalOrderCount = orderInfoListByMonth.stream().count();

                long totalBookCount = orderInfoListByMonth.parallelStream().map(orderDTO -> {
                    return orderDTO.getBookInfoList().stream().count();
                }).reduce(0L, Long::sum);

                Double totalPurchaseAmount = orderInfoListByMonth.parallelStream().map(orderDTO -> {
                    return orderDTO.getBookInfoList().parallelStream().reduce(0.0, (totalPAmount, bookAmount) ->
                            totalPAmount + bookAmount.getAmount(), Double::sum);
                }).reduce(0.0, Double::sum);

                statisticsReportResponse.setOrderedDate(monthsEnum.toString());
                statisticsReportResponse.setTotalPurchaseAmount(totalPurchaseAmount);
                statisticsReportResponse.setTotalBookCount(Long.valueOf(totalBookCount).intValue());
                statisticsReportResponse.setTotalRangeCount(Long.valueOf(totalOrderCount).intValue());

                statisticsReportResponseList.add(statisticsReportResponse);
                return null;
            });
        }
        return statisticsReportResponseList;
    }

    private List<OrderInfo> listSortedOrderInfoByOrDate(String customerId) {
        return orderService.listOrdersByCustomerId(customerId);
    }
}
