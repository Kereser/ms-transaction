package com.emazon.ms_transaction.infra.out.jpa.adapter;

import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.infra.out.jpa.entity.SupplyEntity;
import com.emazon.ms_transaction.infra.out.jpa.mapper.TransactionalEntityMapper;
import com.emazon.ms_transaction.infra.out.jpa.repository.TransactionalJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionJpaAdapter implements ITransactionPersistencePort {

    private final TransactionalJpaRepository transactionalJpaRepository;
    private final TransactionalEntityMapper transactionalEntityMapper;

    @Override
    public void save(Supply model) {
        SupplyEntity entity = transactionalEntityMapper.toEntity(model);

        entity.addReferenceToSupplyArticlesEntities(entity.getSupplyArticleEntity());
        transactionalJpaRepository.save(entity);
    }
}
