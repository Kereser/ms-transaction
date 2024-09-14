package com.emazon.ms_transaction.domain.spi;

import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.model.SupplyArticle;

import java.util.Set;

public interface ITransactionPersistencePort {
    void addSupply(Set<SupplyArticle> dto);
    void save(Supply entity);
}
