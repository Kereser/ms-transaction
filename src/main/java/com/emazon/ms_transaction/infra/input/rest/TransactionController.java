package com.emazon.ms_transaction.infra.input.rest;

import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.application.handler.ITransactionHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionHandler transactionHandler;

    @PostMapping("/supply")
    public ResponseEntity<Void> addSupply(@RequestBody @Valid SupplyReqDTO dto) {
        transactionHandler.addSupply(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sale")
    public ResponseEntity<String> addSale() {
        return ResponseEntity.ok("Sell registered");
    }
}
