package com.example.Receipt.Processor.services;

import com.example.Receipt.Processor.model.Item;
import com.example.Receipt.Processor.model.Receipt;
import com.example.Receipt.Processor.repository.ReceiptRepository;
import com.example.Receipt.Processor.validator.ReceiptValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ReceiptServicesTest {


    @Mock
    private ReceiptRepository receiptRepository;

    @Mock
    private ReceiptValidator receiptValidator;

    @InjectMocks
    private ReceiptServices receiptServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // ✅ Initialize mocks
    }

    @Test
    void processReceipt_ValidReceipt_StoresPoints() {

        Receipt receipt = new Receipt();
        receipt.setTotal("100.00");
        receipt.setRetailer("Retailer123");
        receipt.setItems(List.of(new Item()));
        receipt.setPurchaseDate("2024-02-04");
        receipt.setPurchaseTime("15:37");

        doNothing().when(receiptValidator).validateReceipt(receipt);
        String receiptId = receiptServices.processReceipt(receipt);

        assertNotNull(receiptId);
        verify(receiptRepository, times(1)).storeReceiptPoints(anyString(), anyInt());
    }

    @Test
    void getReceiptPoints_ValidId_ReturnsStoredPoints() {
        when(receiptRepository.getReceiptPoints("receipt-123")).thenReturn(40);

        int points = receiptServices.getReceiptPoints("receipt-123");

        assertEquals(40, points);
    }
}
