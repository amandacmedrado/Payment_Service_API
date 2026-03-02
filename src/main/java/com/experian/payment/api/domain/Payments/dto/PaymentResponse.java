package com.experian.payment.api.domain.Payments.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private String id;
    private BigDecimal originalAmount;
    private BigDecimal appliedDiscount;
    private BigDecimal finalAmount;
    private BigDecimal cashbackAmount;
    private String methodType;
    private String status;
    private LocalDateTime createdAt;

    // Construtor completo
    public PaymentResponse(String id,
                           BigDecimal originalAmount,
                           BigDecimal appliedDiscount,
                           BigDecimal finalAmount,
                           BigDecimal cashbackAmount,
                           String methodType,
                           String status,
                           LocalDateTime createdAt) {

        this.id = id;
        this.originalAmount = originalAmount;
        this.appliedDiscount = appliedDiscount;
        this.finalAmount = finalAmount;
        this.cashbackAmount = cashbackAmount;
        this.methodType = methodType;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters

    public String getId() {
        return id;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public BigDecimal getAppliedDiscount() {
        return appliedDiscount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public String getMethodType() {
        return methodType;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
