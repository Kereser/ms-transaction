package com.emazon.ms_transaction.domain.spi;

import com.emazon.ms_transaction.domain.model.Sale;
import com.emazon.ms_transaction.domain.model.Supply;

public interface ITransactionPersistencePort {
    void save(Supply entity);
    void save(Sale sale);
}
