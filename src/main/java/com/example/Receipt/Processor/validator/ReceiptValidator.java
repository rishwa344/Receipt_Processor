package com.example.Receipt.Processor.validator;

import com.example.Receipt.Processor.model.Item;
import com.example.Receipt.Processor.model.Receipt;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class ReceiptValidator {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void validateReceipt(Receipt receipt) {
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt cannot be null");
        }

        // Validate retailer name
        if (receipt.getRetailer() == null || receipt.getRetailer().trim().isEmpty()) {
            throw new IllegalArgumentException("Retailer name is required");
        }

        // Validate purchase date
        validatePurchaseDate(receipt.getPurchaseDate());

        // Validate purchase time
        validatePurchaseTime(receipt.getPurchaseTime());

        // Validate total amount
        validateTotal(receipt.getTotal());

        // Validate items list
        validateItems(receipt);
    }

    private void validatePurchaseDate(String purchaseDate) {
        if (purchaseDate == null || purchaseDate.trim().isEmpty()) {
            throw new IllegalArgumentException("Purchase Date cannot be null or empty");
        }
        try {
            LocalDate.parse(purchaseDate, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid Purchase Date format. Expected format: yyyy-MM-dd");
        }
    }

    private void validatePurchaseTime(String purchaseTime) {
        if (purchaseTime == null || purchaseTime.trim().isEmpty()) {
            throw new IllegalArgumentException("Purchase Time is required");
        }
        if (!purchaseTime.matches("([01]\\d|2[0-3]):[0-5]\\d")) {
            throw new IllegalArgumentException("Invalid Purchase Time format. Expected format: HH:mm");
        }
    }

    private void validateTotal(String total) {
        if (total == null || total.trim().isEmpty()) {
            throw new IllegalArgumentException("Total amount is required");
        }
        try {
            double totalValue = Double.parseDouble(total);
            if (totalValue < 0) {
                throw new IllegalArgumentException("Total amount cannot be negative");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Total must be a valid numeric value");
        }
    }

    private void validateItems(Receipt receipt) {
        if (receipt.getItems() == null || receipt.getItems().isEmpty()) {
            throw new IllegalArgumentException("Receipt must contain at least one item");
        }
        for (Item item : receipt.getItems()) {
            if (item.getShortDescription() == null || item.getShortDescription().trim().isEmpty()) {
                throw new IllegalArgumentException("Item description cannot be empty");
            }
            if (item.getPrice() == null || item.getPrice().trim().isEmpty()) {
                throw new IllegalArgumentException("Item price is required");
            }
            try {
                double price = Double.parseDouble(item.getPrice());
                if (price < 0) {
                    throw new IllegalArgumentException("Item price cannot be negative");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Item price must be a valid number");
            }
        }
    }
}
