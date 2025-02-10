package com.example.Receipt.Processor.services;

import com.example.Receipt.Processor.model.Item;
import com.example.Receipt.Processor.model.Receipt;
import com.example.Receipt.Processor.validator.ReceiptValidator;
import com.example.Receipt.Processor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptServices {

    private final ReceiptRepository receiptRepository;
    private final ReceiptValidator receiptValidator;

    @Autowired
    public ReceiptServices(ReceiptRepository receiptRepository, ReceiptValidator receiptValidator){
        this.receiptRepository = receiptRepository;
        this.receiptValidator = receiptValidator;
    }

    public String processReceipt(Receipt receipt){
        receiptValidator.validateReceipt(receipt);
        String receiptId = UUID.randomUUID().toString();
        int receiptPoints = calculatePoints(receipt);
        receiptRepository.storeReceiptPoints(receiptId, receiptPoints);
        return receiptId;
    }

    public int getReceiptPoints(String id){
        return receiptRepository.getReceiptPoints(id);
    }

    private int calculatePoints(Receipt receipt) {
        int points = 0;

        double total = Double.parseDouble(receipt.getTotal()); // Parse total once

        // 25 points if total is a multiple of 0.25
        if (total % 0.25 == 0) points += 25;

        // 50 points if total is a round dollar amount
        if (total == Math.floor(total)) points += 50;

        // Points for alphanumeric characters in retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        // Points for every two items
        points += (receipt.getItems().size() >> 1) * 5; // Faster than size()/2 * 5

        // Process items for description-based points
        for (var item : receipt.getItems()) {
            points += calculateItemPoints(item);
        }

        // Parse date and time only once
        String[] dateParts = receipt.getPurchaseDate().split("-");
        int day = Integer.parseInt(dateParts[2]);

        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);

        // 6 points if purchase day is odd
        if ((day & 1) == 1) points += 6;  // Faster than `day % 2 != 0`

        // 10 points if purchase time is between 2:00 PM and 4:00 PM
        if (hour >= 14 && hour < 16) points += 10;

        return points;
    }

    // Helper method to calculate points for items
    private int calculateItemPoints(Item item) {
        String description = item.getShortDescription();

        // Handle null shortDescription
        if (description == null || description.trim().isEmpty()) {
            return 0; // No points if description is missing or empty
        }

        description = description.trim();

        if (description.length() % 3 == 0) {
            return (int) Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
        }
        return 0;
    }

}
