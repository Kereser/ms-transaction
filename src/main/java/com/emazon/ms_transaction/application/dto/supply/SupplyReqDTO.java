package com.emazon.ms_transaction.application.dto.supply;

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
public class SupplyReqDTO {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Valid
    private Set<@Valid ItemQuantity> item;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemQuantity {
        @NotNull
        @Positive
        private Long articleId;

        @NotNull
        @Positive
        private Long quantity;
    }
}
