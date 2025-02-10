package com.example.Receipt.Processor.controller;

import com.example.Receipt.Processor.services.ReceiptServices;
import com.example.Receipt.Processor.model.Receipt;
import com.example.Receipt.Processor.validator.ReceiptValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptServices receiptService;

    private final ReceiptValidator receiptValidator;

    @Autowired
    public ReceiptController(ReceiptServices receiptService, ReceiptValidator receiptValidator){
        this.receiptService = receiptService;
        this.receiptValidator = receiptValidator;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String,String>> processReceipt(@RequestBody Receipt receipt){

        try {
            receiptValidator.validateReceipt(receipt);
            String receiptId = receiptService.processReceipt(receipt);
            return new ResponseEntity<>(Map.of("id", receiptId), HttpStatusCode.valueOf(200));
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("Bad Request: ", e.getMessage()), HttpStatusCode.valueOf(400));
        }
        catch (Exception e) {
            return new ResponseEntity<>(Map.of("Internal Server Error: ", e.getMessage()), HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, String>> getPoints(@PathVariable String id){

        try {
            int points = receiptService.getReceiptPoints(id);
            return ResponseEntity.ok(Map.of("points", String.valueOf(points)));
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("Bad Request: ", e.getMessage()), HttpStatusCode.valueOf(400));
        }
        catch (Exception e) {
            return new ResponseEntity<>(Map.of("Internal Server Error: ", e.getMessage()), HttpStatusCode.valueOf(500));
        }
    }
}
