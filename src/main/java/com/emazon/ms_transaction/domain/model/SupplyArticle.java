package com.emazon.ms_transaction.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplyArticle {
    private Long id;
    private Supply supply;
    private Long articleId;
    private Long quantity;
}

