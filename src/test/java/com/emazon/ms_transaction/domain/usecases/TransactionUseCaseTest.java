package com.emazon.ms_transaction.domain.usecases;

import com.emazon.ms_transaction.ConsUtils;
import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.model.SupplyArticle;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class TransactionUseCaseTest {

    @Mock
    private ITransactionPersistencePort transactionPersistencePort;

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    private final SupplyArticle supplyArticle = new SupplyArticle(ConsUtils.LONG_1, ConsUtils.LONG_1);
    private final Supply supply = new Supply(ConsUtils.LONG_1, ConsUtils.LONG_1, Set.of(supplyArticle));

    @Test
    void Should_CorrectlyInteracts_With_PersistencePort() {
        transactionUseCase.addSupply(supply);

        Mockito.verify(transactionPersistencePort, Mockito.times(ConsUtils.INTEGER_1)).addSupply(supply.getSupplyArticle());
        Mockito.verify(transactionPersistencePort, Mockito.times(ConsUtils.INTEGER_1)).save(supply);
    }
}