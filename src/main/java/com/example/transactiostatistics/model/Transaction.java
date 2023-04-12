package com.serbit.transaction.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serbit.transaction.demo.constant.DateTimeConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by David on 01 Apr, 2023
 **/
@Data
public class Transaction {

    private String id;

    private BigDecimal amount;

    private LocalDateTime timestamp;
}
