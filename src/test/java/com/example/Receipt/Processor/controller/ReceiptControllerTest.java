package com.example.Receipt.Processor.controller;

import com.example.Receipt.Processor.services.ReceiptServices;
import com.example.Receipt.Processor.model.Receipt;
import com.example.Receipt.Processor.validator.ReceiptValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReceiptControllerTest {

    @Mock
    private ReceiptServices receiptServices;

    @Mock private ReceiptValidator receiptValidator;

    @InjectMocks
    private ReceiptController receiptController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processReceipt_ValidReceipt_ReturnsReceiptId() {
        Receipt receipt = new Receipt();
        doNothing().when(receiptValidator).validateReceipt(receipt);
        when(receiptServices.processReceipt(receipt)).thenReturn("receipt-123");
        ResponseEntity<Map<String, String>> response = receiptController.processReceipt(receipt);
        assertEquals("receipt-123", response.getBody().get("id"));
    }

    @Test
    void getPoints_ValidReceiptId_ReturnsPoints() {
        when(receiptServices.getReceiptPoints("receipt-123")).thenReturn(50);

        ResponseEntity<Map<String, String>> response = receiptController.getPoints("receipt-123");

        assertEquals("50", response.getBody().get("points"));
    }
}
