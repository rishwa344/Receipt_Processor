package com.example.Receipt.Processor.validator;

import com.example.Receipt.Processor.model.Item;
import com.example.Receipt.Processor.model.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ReceiptValidatorTest {

    private ReceiptValidator receiptValidator;
    private Receipt validReceipt;

    @BeforeEach
    void setUp() {
        receiptValidator = new ReceiptValidator();

        validReceipt = new Receipt();
        validReceipt.setRetailer("Target");
        validReceipt.setPurchaseDate("2022-01-01");
        validReceipt.setPurchaseTime("13:01");
        validReceipt.setTotal("35.35");

        Item item1 = new Item();
        item1.setShortDescription("Mountain Dew 12PK");
        item1.setPrice("6.49");

        validReceipt.setItems(List.of(item1));
    }

    @Test
    void testValidReceipt_ShouldPass() {
        assertDoesNotThrow(() -> receiptValidator.validateReceipt(validReceipt));
    }

    @Test
    void testNullReceipt_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(null));
        assertEquals("Receipt cannot be null", exception.getMessage());
    }

    @Test
    void testEmptyRetailer_ShouldThrowException() {
        validReceipt.setRetailer("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(validReceipt));
        assertEquals("Retailer name is required", exception.getMessage());
    }

    @Test
    void testInvalidDateFormat_ShouldThrowException() {
        validReceipt.setPurchaseDate("01-01-2022");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(validReceipt));
        assertEquals("Invalid Purchase Date format. Expected format: yyyy-MM-dd", exception.getMessage());
    }

    @Test
    void testInvalidTimeFormat_ShouldThrowException() {
        validReceipt.setPurchaseTime("25:61");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(validReceipt));
        assertEquals("Invalid Purchase Time format. Expected format: HH:mm", exception.getMessage());
    }

    @Test
    void testNegativeTotal_ShouldThrowException() {
        validReceipt.setTotal("-10.00");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(validReceipt));
        assertEquals("Total amount cannot be negative", exception.getMessage());
    }

    @Test
    void testEmptyItemsList_ShouldThrowException() {
        validReceipt.setItems(List.of());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(validReceipt));
        assertEquals("Receipt must contain at least one item", exception.getMessage());
    }

    @Test
    void testEmptyItemDescription_ShouldThrowException() {
        validReceipt.getItems().get(0).setShortDescription("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> receiptValidator.validateReceipt(validReceipt));
        assertEquals("Item description cannot be empty", exception.getMessage());
    }
}
