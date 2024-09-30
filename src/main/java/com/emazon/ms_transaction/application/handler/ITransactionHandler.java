package com.emazon.ms_transaction.application.handler;

import com.emazon.ms_transaction.application.dto.SaleReqDTO;
import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;

public interface ITransactionHandler {
    void addSupply(SupplyReqDTO dto);
    void registerSale(SaleReqDTO saleReqDTO);
}
