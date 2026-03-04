package com.experian.payment.api.domain.Products.dto;

import com.experian.payment.api.domain.Products.Product;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;
    private String uuid;
    private String name;
    private BigDecimal price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.uuid = product.getUuid();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
