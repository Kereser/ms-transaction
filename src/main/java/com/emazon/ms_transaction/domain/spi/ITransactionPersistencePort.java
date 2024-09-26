package com.emazon.ms_transaction.domain.spi;

import com.emazon.ms_transaction.domain.model.Supply;

public interface ITransactionPersistencePort {
    void save(Supply entity);
}
