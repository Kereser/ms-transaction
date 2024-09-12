package com.emazon.ms_transaction.application.handler;

import com.emazon.ms_transaction.application.dto.SupplyReqDTO;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionHandler implements ITransactionHandler {

    private final ITransactionServicePort transactionServicePort;

    @Override
    public String addSupply(SupplyReqDTO dto) {
        return "";
    }
}
