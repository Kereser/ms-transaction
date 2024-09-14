package com.emazon.ms_transaction.application.handler;

import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.application.mapper.TransactionDTOMapper;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionHandler implements ITransactionHandler {

    private final ITransactionServicePort transactionServicePort;
    private final TransactionDTOMapper mapper;

    @Override
    public void addSupply(SupplyReqDTO dto) {
        transactionServicePort.addSupply(mapper.toModel(dto));
    }
}
