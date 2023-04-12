package com.example.transactiostatistics.service;


import com.example.transactiostatistics.payload.request.TransactionRequest;
import com.example.transactiostatistics.payload.response.TransactionStatisticsResponse;


public interface TransactionService {

    void saveTransaction(TransactionRequest request);

    TransactionStatisticsResponse getStats();

    void deleteAllTransactions();
}
