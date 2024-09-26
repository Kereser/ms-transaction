package com.emazon.ms_transaction.domain.usecases;

import com.emazon.ms_transaction.application.mapper.TransactionDTOMapper;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.domain.spi.StockFeignPort;

import java.time.LocalDateTime;

public class TransactionUseCase implements ITransactionServicePort {

    private final ITransactionPersistencePort transactionPersistencePort;
    private final StockFeignPort stockFeignPort;
    private final TransactionDTOMapper transactionDTOMapper;

    public TransactionUseCase(ITransactionPersistencePort transactionPersistencePort, StockFeignPort stockFeignPort, TransactionDTOMapper transactionDTOMapper) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.stockFeignPort = stockFeignPort;
        this.transactionDTOMapper = transactionDTOMapper;
    }

    @Override
    public void addSupply(Supply supply) {
        stockFeignPort.addSupply(transactionDTOMapper.toSetDTO(supply.getSupplyArticle()));

        save(supply);
    }

    private void save(Supply supply) {
        supply.setTransactionDate(LocalDateTime.now());

        transactionPersistencePort.save(supply);
    }
}
