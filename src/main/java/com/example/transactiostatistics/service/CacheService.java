package com.example.transactiostatistics.service;

import com.example.transactiostatistics.model.Transaction;

import org.springframework.stereotype.Service;
import wiremock.com.google.common.cache.CacheBuilder;
import wiremock.com.google.common.cache.CacheLoader;
import wiremock.com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

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
