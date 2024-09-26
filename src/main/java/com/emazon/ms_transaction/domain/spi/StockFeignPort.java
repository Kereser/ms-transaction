package com.emazon.ms_transaction.domain.spi;

import com.emazon.ms_transaction.application.dto.supply.SupplyReqDTO;
import com.emazon.ms_transaction.infra.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@FeignClient(name = "MS-STOCK", url = "${external.feign.url.ms-stock}", configuration = FeignConfig.class)
public interface StockFeignPort {

    @PostMapping(value = "/articles/supply", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addSupply(Set<SupplyReqDTO.ItemQuantity> dto);
}
