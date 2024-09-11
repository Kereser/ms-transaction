package com.emazon.ms_transaction.application.handler;

import com.emazon.ms_transaction.application.dto.SupplyReqDTO;

public interface ITransactionHandler {
    String addSupply(SupplyReqDTO dto);
}
