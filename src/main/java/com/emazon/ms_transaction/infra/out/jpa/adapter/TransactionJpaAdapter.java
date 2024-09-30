package com.emazon.ms_transaction.infra.out.jpa.adapter;

import com.emazon.ms_transaction.domain.model.Sale;
import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.infra.out.jpa.entity.SaleEntity;
import com.emazon.ms_transaction.infra.out.jpa.entity.SupplyEntity;
import com.emazon.ms_transaction.infra.out.jpa.mapper.TransactionalEntityMapper;
import com.emazon.ms_transaction.infra.out.jpa.repository.SaleJpaRepository;
import com.emazon.ms_transaction.infra.out.jpa.repository.SupplyJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionJpaAdapter implements ITransactionPersistencePort {

    private final SupplyJpaRepository supplyJpaRepository;
    private final SaleJpaRepository saleJpaRepository;
    private final TransactionalEntityMapper transactionalEntityMapper;

    @Override
    public void save(Supply model) {
        SupplyEntity entity = transactionalEntityMapper.supplyToSupplyEntity(model);

        entity.addReferenceToSupplyArticlesEntities(entity.getSupplyArticleEntity());
        supplyJpaRepository.save(entity);
    }

    @Override
    public void save(Sale sale) {
        SaleEntity entity = transactionalEntityMapper.saleToSaleEntity(sale);

        entity.addReferenceToSaleArticles(entity.getSaleArticles());
        saleJpaRepository.save(entity);
    }
}
