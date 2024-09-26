package com.emazon.ms_transaction.infra.config;

import com.emazon.ms_transaction.application.mapper.TransactionDTOMapper;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.domain.usecases.TransactionUseCase;
import com.emazon.ms_transaction.domain.spi.StockFeignPort;
import com.emazon.ms_transaction.infra.out.jpa.adapter.TransactionJpaAdapter;
import com.emazon.ms_transaction.infra.out.jpa.mapper.TransactionalEntityMapper;
import com.emazon.ms_transaction.infra.out.jpa.repository.TransactionalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    private final StockFeignPort stockFeignPort;
    private final TransactionalJpaRepository transactionalJpaRepository;
    private final TransactionDTOMapper transactionDTOMapper;
    private final TransactionalEntityMapper transactionalEntityMapper;

    @Bean
    public ITransactionPersistencePort transactionPersistencePort() {
        return new TransactionJpaAdapter(transactionalJpaRepository, transactionalEntityMapper);
    }

    @Bean
    public ITransactionServicePort transactionServicePort() {
        return new TransactionUseCase(transactionPersistencePort(), stockFeignPort, transactionDTOMapper);
    }
}
