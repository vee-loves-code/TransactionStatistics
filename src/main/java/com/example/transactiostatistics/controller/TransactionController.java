package com.serbit.transaction.demo.controller;

import com.serbit.transaction.demo.dto.ResponseObject;
import com.serbit.transaction.demo.dto.TransactionCreationRequest;
import com.serbit.transaction.demo.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by David on 01 Apr, 2023
 **/
@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {

    private TransactionService transactionService;


    /**
     *
     * API to save Transaction
     */
    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody @Valid TransactionCreationRequest request) {

        transactionService.saveTransaction(request);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /**
     * API to get all transactions stats
     */
    @GetMapping
    public ResponseEntity<?> getTransactionStats() {

        return new ResponseEntity<>(
                new ResponseObject(
                        ResponseObject.ResponseStatus.SUCCESSFUL, "",
                        transactionService.getStats()),
                HttpStatus.OK);
    }

    /**
     * API to delete all Transactions
     */
    @DeleteMapping
    public ResponseEntity<?> deleteAllTransactions() {

        transactionService.deleteAllTransactions();

        return ResponseEntity.noContent().build();
    }
}
