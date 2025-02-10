package com.example.Receipt.Processor.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ReceiptRepository {

    private final Map<String,Integer> receiptMap = new HashMap<>();

    public void storeReceiptPoints(String receiptId, int receiptPoints) {
        receiptMap.put(receiptId,receiptPoints);
    }

    public int getReceiptPoints(String receiptId) {

        if(receiptMap.containsKey(receiptId)) {
            return receiptMap.get((receiptId));
        }
        throw new IllegalArgumentException("Receipt not found");
    }
}
