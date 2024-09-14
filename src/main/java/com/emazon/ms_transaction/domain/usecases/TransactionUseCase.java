package com.emazon.ms_transaction.domain.usecases;

import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;

public class TransactionUseCase implements ITransactionServicePort {

    private final ITransactionPersistencePort transactionPersistencePort;

    public TransactionUseCase(ITransactionPersistencePort transactionPersistencePort) {
        this.transactionPersistencePort = transactionPersistencePort;
    }

    @Override
    public void addSupply(Supply supply) {
        transactionPersistencePort.addSupply(supply.getSupplyArticle());

        transactionPersistencePort.save(supply);
    }
}
