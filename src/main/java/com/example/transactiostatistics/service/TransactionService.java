package com.serbit.transaction.demo.service;

import com.serbit.transaction.demo.dto.TransactionCreationRequest;
import com.serbit.transaction.demo.dto.TransactionStatsDto;
import com.serbit.transaction.demo.model.Transaction;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by David on 01 Apr, 2023
 **/
public interface TransactionService {

    void saveTransaction(TransactionCreationRequest request);

    TransactionStatsDto getStats();

    void deleteAllTransactions();
}
