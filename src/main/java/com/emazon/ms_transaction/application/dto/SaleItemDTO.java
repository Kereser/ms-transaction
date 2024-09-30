package com.emazon.ms_transaction.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemDTO {
    private Long articleId;
    private Long quantity;
}
