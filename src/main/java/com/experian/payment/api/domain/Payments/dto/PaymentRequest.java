package com.experian.payment.api.domain.Payments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaymentRequest {

    private List<Product> products;
    private PaymentMethodType paymentMethodType;

    public PaymentRequest(List<Product> products, PaymentMethodType paymentMethodType) {
        this.products = products;
        this.paymentMethodType = paymentMethodType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getPaymentMethodType() {
        return paymentMethodType.toString();
    }

    public void setPaymentMethodType(PaymentMethodType paymentMethodType) {
        this.paymentMethodType = paymentMethodType;
    }

    public static class Product {

        @JsonProperty("product_id")
        private Long productId;

        private Integer quantity;


        public Product(Long productId, Integer quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public enum PaymentMethodType {
        CREDIT_CARD,
        DEBIT_CARD,
        PIX,
    }
}
