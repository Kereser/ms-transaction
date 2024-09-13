package com.emazon.ms_transaction.infra.config;

import com.emazon.ms_transaction.application.mapper.TransactionDTOMapper;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.domain.usecases.TransactionUseCase;
import com.emazon.ms_transaction.infra.feign.adapters.StockFeignAdapter;
import com.emazon.ms_transaction.infra.out.jpa.adapter.TransactionJpaAdapter;
import com.emazon.ms_transaction.infra.out.jpa.repository.TransactionalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final StockFeignAdapter stockFeignAdapter;
    private final TransactionDTOMapper transactionDTOMapper;
    private final TransactionalJpaRepository transactionalJpaRepository;

    @Bean
    public ITransactionPersistencePort transactionPersistencePort() {
        return new TransactionJpaAdapter(stockFeignAdapter, transactionalJpaRepository, transactionDTOMapper);
    }

    @Bean
    public ITransactionServicePort transactionServicePort() {
        return new TransactionUseCase(transactionPersistencePort());
    }
}
