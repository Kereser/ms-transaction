package com.emazon.ms_transaction;

import com.emazon.ms_transaction.application.dto.SaleItemDTO;
import com.emazon.ms_transaction.application.dto.SaleReqDTO;

import java.util.Set;

public class TestCreationUtils {
    private TestCreationUtils() {
    }

    public static SaleItemDTO createSaleItemDTO() {
        return SaleItemDTO.builder()
                .articleId(ConsUtils.LONG_1)
                .quantity(ConsUtils.LONG_1)
                .build();
    }

    public static SaleReqDTO createSaleReqDTO() {
        return SaleReqDTO.builder()
                .userId(ConsUtils.LONG_1)
                .saleArticles(Set.of(createSaleItemDTO()))
                .build();
    }
}
