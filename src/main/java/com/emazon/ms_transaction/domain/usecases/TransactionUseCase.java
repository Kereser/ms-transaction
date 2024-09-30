package com.emazon.ms_transaction.domain.usecases;

import com.emazon.ms_transaction.application.mapper.TransactionDTOMapper;
import com.emazon.ms_transaction.domain.api.ITransactionServicePort;
import com.emazon.ms_transaction.domain.model.Sale;
import com.emazon.ms_transaction.domain.model.SaleStatus;
import com.emazon.ms_transaction.domain.model.Supply;
import com.emazon.ms_transaction.domain.spi.ITransactionPersistencePort;
import com.emazon.ms_transaction.domain.spi.StockFeignPort;

import java.time.LocalDateTime;

public class TransactionUseCase implements ITransactionServicePort {

    private final ITransactionPersistencePort transactionPersistencePort;
    private final StockFeignPort stockFeignPort;
    private final TransactionDTOMapper transactionDTOMapper;

    public TransactionUseCase(ITransactionPersistencePort transactionPersistencePort, StockFeignPort stockFeignPort, TransactionDTOMapper transactionDTOMapper) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.stockFeignPort = stockFeignPort;
        this.transactionDTOMapper = transactionDTOMapper;
    }

    @Override
    public void addSupply(Supply supply) {
        stockFeignPort.addSupply(transactionDTOMapper.toSetDTO(supply.getSupplyArticle()));

        save(supply);
    }

    @Override
    public void registerSale(Sale sale) {
        saveInProgressSale(sale);
        processPayment(sale);
        saveCompletedSale(sale);
    }

    private void saveCompletedSale(Sale sale) {
        makeSaleCompleted(sale);
        save(sale);
    }

    private void saveInProgressSale(Sale sale) {
        makeSaleInProgress(sale);
        save(sale);
    }

    private void makeSaleInProgress(Sale sale) {
        sale.setStatus(SaleStatus.IN_PROGRESS);
    }

    private void makeSaleCompleted(Sale sale) {
        sale.setStatus(SaleStatus.COMPLETED);
    }

    private void processPayment(Sale sale) {
        assert sale.getSaleArticles() != null;
        /*
        * try {
        *   ms-xxx to get payment data?
        * } catch (Exception e) {
        *   updateSaleToFailed();
        * }
        * */
    }

    private void save(Sale sale) {
        sale.setTransactionDate(LocalDateTime.now());

        transactionPersistencePort.save(sale);
    }

    private void save(Supply supply) {
        supply.setTransactionDate(LocalDateTime.now());

        transactionPersistencePort.save(supply);
    }
}
