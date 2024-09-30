package com.emazon.ms_transaction.infra.config;

import com.emazon.ms_transaction.application.mapper.TransactionDTOMapper;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.domain.usecases.TransactionUseCase;
import com.emazon.ms_transaction.domain.spi.StockFeignPort;
import com.emazon.ms_transaction.infra.out.jpa.adapter.TransactionJpaAdapter;
import com.emazon.ms_transaction.infra.out.jpa.mapper.TransactionalEntityMapper;
import com.emazon.ms_transaction.infra.out.jpa.repository.SaleJpaRepository;
import com.emazon.ms_transaction.infra.out.jpa.repository.SupplyJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final StockFeignPort stockFeignPort;
    private final SupplyJpaRepository supplyJpaRepository;
    private final SaleJpaRepository saleJpaRepository;
    private final TransactionDTOMapper transactionDTOMapper;
    private final TransactionalEntityMapper transactionalEntityMapper;

    @Bean
    public ITransactionPersistencePort transactionPersistencePort() {
        return new TransactionJpaAdapter(supplyJpaRepository, saleJpaRepository, transactionalEntityMapper);
    }

    @Bean
    public ITransactionServicePort transactionServicePort() {
        return new TransactionUseCase(transactionPersistencePort(), stockFeignPort, transactionDTOMapper);
    }
}
