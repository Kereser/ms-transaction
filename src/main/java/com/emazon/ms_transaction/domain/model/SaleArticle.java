package com.emazon.ms_transaction.domain.model;

public class SaleArticle {
    private Long id;
    private Long articleId;
    private Long quantity;

    public SaleArticle() {
    }

    public SaleArticle(Long id, Long articleId, Long quantity) {
        this.id = id;
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
