package com.emazon.ms_transaction.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleReqDTO {
    @NotNull
    @Positive
    private Long userId;
    @NotNull
    private Set<@Valid SaleItemDTO> saleArticles;
}
