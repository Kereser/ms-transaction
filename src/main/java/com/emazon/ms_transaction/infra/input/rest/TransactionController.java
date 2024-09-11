package com.emazon.ms_transaction.infra.input.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping("/supply")
    public ResponseEntity<String> addSupply() {
        return ResponseEntity.ok("Supply registered");
    }

    @PostMapping("/sale")
    public ResponseEntity<String> addSale() {
        return ResponseEntity.ok("Sell registered");
    }
}
