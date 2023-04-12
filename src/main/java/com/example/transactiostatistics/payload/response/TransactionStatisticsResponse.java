package com.example.transactiostatistics.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatisticsResponse {

    //
    private BigDecimal sum;
    private BigDecimal average;
    private BigDecimal max;
    private BigDecimal min;
    private long count;
}
