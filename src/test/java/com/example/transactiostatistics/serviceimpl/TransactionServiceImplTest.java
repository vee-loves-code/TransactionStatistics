package com.serbit.transaction.demo.service.impl;

import com.serbit.transaction.demo.dto.TransactionCreationRequest;
import com.serbit.transaction.demo.dto.TransactionStatsDto;
import com.serbit.transaction.demo.model.Transaction;
import com.serbit.transaction.demo.service.CacheService;
import com.serbit.transaction.demo.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import wiremock.com.google.common.cache.CacheBuilder;
import wiremock.com.google.common.cache.CacheLoader;
import wiremock.com.google.common.cache.LoadingCache;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/**
 * Created by David on 02 Apr, 2023
 **/
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TransactionServiceImpl.class, ModelMapper.class})
class TransactionServiceImplTest {

    @MockBean
    private CacheService cacheService;

    @Autowired
    private TransactionService transactionService;

    private LoadingCache<String, Transaction> transactionCache;

    @BeforeEach
    void setUp() {
        transactionCache =  CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Transaction>() {

                    @Override
                    public Transaction load(final String key) {
                        return null;
                    }
                });

        Transaction t = new Transaction();
        t.setId(UUID.randomUUID().toString());
        t.setAmount(BigDecimal.valueOf(45));
        t.setTimestamp(LocalDateTime.now());
        transactionCache.put(t.getId(), t);

        t = new Transaction();
        t.setId(UUID.randomUUID().toString());
        t.setAmount(BigDecimal.valueOf(50));
        t.setTimestamp(LocalDateTime.now());
        transactionCache.put(t.getId(), t);
    }


    @Test
    void saveTransaction() {
        TransactionCreationRequest req = new TransactionCreationRequest();
        req.setAmount(BigDecimal.valueOf(200));
        req.setTimestamp(LocalDateTime.now());

        // assumptions
        when(cacheService.getCache()).thenReturn(transactionCache);

        // verify
        transactionService.saveTransaction(req);
        verify(cacheService, times(1)).getCache();
    }

    @Test
    void getStats() {
        when(cacheService.getCache()).thenReturn(transactionCache);

        // validate transaction stats
        TransactionStatsDto stats = transactionService.getStats();
        assertThat(stats.getSum().doubleValue()).isEqualTo(95.00D);
        assertThat(stats.getAvg().doubleValue()).isEqualTo(47.50D);
        assertThat(stats.getMax().doubleValue()).isEqualTo(50D);
        assertThat(stats.getMin().doubleValue()).isEqualTo(45D);
        assertThat(stats.getCount()).isEqualTo(2L);
    }

    @Test
    void deleteAllTransactions() {
        when(cacheService.getCache()).thenReturn(transactionCache);
        transactionService.deleteAllTransactions();

        // after delete, assert that transaction size is 0
        Collection<Transaction> transactionCollection =  transactionCache.asMap().values();
        Set<Transaction> transactions = new HashSet<>(transactionCollection);
        assertThat(transactions.size()).isEqualTo(0);
    }

}