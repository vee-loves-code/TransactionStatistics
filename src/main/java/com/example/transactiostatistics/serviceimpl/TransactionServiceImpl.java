package com.serbit.transaction.demo.service.impl;

import com.serbit.transaction.demo.dto.TransactionCreationRequest;
import com.serbit.transaction.demo.dto.TransactionStatsDto;
import com.serbit.transaction.demo.model.Transaction;
import com.serbit.transaction.demo.service.CacheService;
import com.serbit.transaction.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by David on 01 Apr, 2023
 **/
@Service
@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ModelMapper modelMapper;

    private final CacheService cacheService;

    @Override
    public void saveTransaction(TransactionCreationRequest request) {

        String uuId = UUID.randomUUID().toString();
        log.info("random uuid: {}", uuId);
        Transaction transaction = modelMapper.map(request, Transaction.class);
        transaction.setId(uuId);
        cacheService.getCache().put(uuId, transaction);
    }


    @Override
    public TransactionStatsDto getStats() {

        Collection<Transaction> transactionCollection =  cacheService.getCache().asMap().values();
        Set<Transaction> transactions = new HashSet<>(transactionCollection);

        // populate stats
        TransactionStatsDto stats = new TransactionStatsDto();
        stats.setSum(transactions.stream().map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP));
        stats.setAvg(transactions.size() > 0 ?
                stats.getSum().divide(new BigDecimal(transactions.size()), RoundingMode.HALF_UP )
                        .setScale(2, RoundingMode.HALF_UP) :
                BigDecimal.ZERO);
        stats.setMax(transactions.stream().map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::max)
                .setScale(2, RoundingMode.HALF_UP));
        stats.setMin(transactions.stream().map(Transaction::getAmount)
                .min(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP));
        stats.setCount(transactions.size());
        return stats;
    }

    @Override
    public void deleteAllTransactions() {
        cacheService.getCache().invalidateAll();
    }
}
