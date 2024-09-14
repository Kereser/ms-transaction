package com.emazon.ms_transaction.domain.api;

import com.emazon.ms_transaction.domain.model.Supply;

public interface ITransactionServicePort {
    void addSupply(Supply supply);
}
