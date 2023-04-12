package com.serbit.transaction.demo.service;

import com.serbit.transaction.demo.model.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wiremock.com.google.common.cache.CacheBuilder;
import wiremock.com.google.common.cache.CacheLoader;
import wiremock.com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by David on 02 Apr, 2023
 **/
@Service
public class CacheService {

    private final LoadingCache<String, Transaction> transactionCache;

    public CacheService() {

        transactionCache = CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Transaction>() {

                    @Override
                    public Transaction load(final String key) {
                        return null;
                    }
                });
    }

    public LoadingCache<String, Transaction> getCache() {
        return transactionCache;
    }
}
