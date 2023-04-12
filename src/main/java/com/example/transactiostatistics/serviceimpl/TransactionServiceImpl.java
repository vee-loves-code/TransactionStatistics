package com.example.transactiostatistics.serviceimpl;

import com.example.transactiostatistics.model.Transaction;
import com.example.transactiostatistics.payload.request.TransactionRequest;
import com.example.transactiostatistics.payload.response.TransactionStatisticsResponse;
import com.example.transactiostatistics.service.CacheService;
import com.example.transactiostatistics.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ModelMapper modelMapper;

    private final CacheService cacheService;

    @Override
    public void saveTransaction(TransactionRequest request) {

        String uuId = UUID.randomUUID().toString();
        log.info("random uuid: {}", uuId);
        Transaction transaction = modelMapper.map(request, Transaction.class);
        transaction.setId(uuId);
        cacheService.getCache().put(uuId, transaction);
    }


    @Override
    public TransactionStatisticsResponse getStats() {

        Collection<Transaction> transactionCollection =  cacheService.getCache().asMap().values();
        Set<Transaction> transactions = new HashSet<>(transactionCollection);

        // populate the statistics
        TransactionStatisticsResponse transactionStats = new TransactionStatisticsResponse();
        transactionStats.setSum(transactions.stream().map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP));
        transactionStats.setAverage(transactions.size() > 0 ?
                transactionStats.getSum().divide(new BigDecimal(transactions.size()), RoundingMode.HALF_UP )
                        .setScale(2, RoundingMode.HALF_UP) :
                BigDecimal.ZERO);
        transactionStats.setMax(transactions.stream().map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::max)
                .setScale(2, RoundingMode.HALF_UP));
        transactionStats.setMin(transactions.stream().map(Transaction::getAmount)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP));
        transactionStats.setCount(transactions.size());
        return transactionStats;
    }

    @Override
    public void deleteAllTransactions() {
        cacheService.getCache().invalidateAll();
    }
}
