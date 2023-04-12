package com.serbit.transaction.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.serbit.transaction.demo.dto.TransactionCreationRequest;
import com.serbit.transaction.demo.dto.TransactionStatsDto;
import com.serbit.transaction.demo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void testSaveTransaction() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        String req = "{\n" +
                "  \"amount\": 3,\n" +
                "  \"timestamp\": \"2022-07-17T09:59:51.312Z\"\n" +
                "}";

        doNothing().when(transactionService).saveTransaction(any());

        MvcResult mvcResult = mockMvc.perform(post("/transactions")
                .content(req)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    void testGetTransactionStats() throws Exception {

        TransactionStatsDto stats = new TransactionStatsDto();
        stats.setSum(BigDecimal.valueOf(50));
        stats.setAvg(BigDecimal.valueOf(30));
        stats.setMax(BigDecimal.valueOf(50));
        stats.setMin(BigDecimal.valueOf(3));
        stats.setCount(6);

        when(transactionService.getStats()).thenReturn(stats);

        MvcResult mvcResult = mockMvc.perform(get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Successful"))
                .andExpect(jsonPath("$.message").value(""))
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data").isMap())
                .andExpect(jsonPath("$.data.sum").value("50"))
                .andReturn();

    }

    @Test
    void testDeleteAllTransactions() throws Exception {

        doNothing().when(transactionService).deleteAllTransactions();

        MvcResult mvcResult = mockMvc.perform(delete("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

    }
}
