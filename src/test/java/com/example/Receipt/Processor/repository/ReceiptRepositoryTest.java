package com.example.Receipt.Processor.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReceiptRepositoryTest {

    private ReceiptRepository receiptRepository;

    @BeforeEach
    void setUp() {
        receiptRepository = new ReceiptRepository();
    }

    @Test
    void storeAndRetrieveReceiptPoints_ValidReceiptId_ReturnsCorrectPoints() {
        String receiptId = "receipt-123";
        receiptRepository.storeReceiptPoints(receiptId, 100);

        int retrievedPoints = receiptRepository.getReceiptPoints(receiptId);

        assertEquals(100, retrievedPoints);
    }

    @Test
    void getReceiptPoints_NonExistentReceiptId_ReturnsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            receiptRepository.getReceiptPoints("invalid123");
        });

        assertEquals("Receipt not found", exception.getMessage());
    }
}
