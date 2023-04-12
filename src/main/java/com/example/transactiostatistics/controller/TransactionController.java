package com.example.transactiostatistics.controller;

import com.example.transactiostatistics.payload.request.ResponseObject;
import com.example.transactiostatistics.payload.request.TransactionRequest;
import com.example.transactiostatistics.service.TransactionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping(value = "/transactions")
public class TransactionController {

    private TransactionService transactionService;

     //  PostTransaction API
    @PostMapping
    public ResponseEntity<?> postTransaction(@RequestBody @Valid TransactionRequest request) {

        transactionService.saveTransaction(request);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


     //  Fetch statistics of transactions API
    @GetMapping
    public ResponseEntity<?> getTransactionStats() {

        return new ResponseEntity<>(
                new ResponseObject(
                        ResponseObject.ResponseStatus.SUCCESSFUL, "",
                        transactionService.getStats()),
                HttpStatus.OK);
    }


     // delete all Transactions API
    @DeleteMapping
    public ResponseEntity<?> deleteAllTransactions() {

        transactionService.deleteAllTransactions();

        return ResponseEntity.noContent().build();
    }
}
