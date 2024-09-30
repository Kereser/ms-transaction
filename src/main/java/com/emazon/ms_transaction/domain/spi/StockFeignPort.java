package com.emazon.ms_transaction.domain.spi;

import com.emazon.ms_transaction.ConsUtils;
import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@FeignClient(name = ConsUtils.MS_STOCK, url = ConsUtils.MS_STOCK_URL, configuration = FeignConfig.class)
public interface StockFeignPort {

    @PostMapping(value = ConsUtils.ARTICLES_SUPPLY_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    void addSupply(Set<SupplyReqDTO.ItemQuantity> dto);
}
