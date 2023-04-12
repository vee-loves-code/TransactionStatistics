package com.example.transactiostatistics.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transaction {
//model object
    private String id;

    private BigDecimal amount;

    private LocalDateTime timestamp;
}
