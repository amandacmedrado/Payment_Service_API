package com.experian.payment.api.domain.Payments;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, length = 36, unique = true)
    private String uuid;

    @Column(name = "original_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal originalAmount;

    @Column(name = "final_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalAmount;

    @Column(name = "cashback_amount", precision = 10, scale = 2)
    private BigDecimal cashbackAmount;

    @Column(name = "applied_discount", precision = 10, scale = 2)
    private BigDecimal appliedDiscount;


    @Column(name = "method_type", nullable = false, length = 30)
    private String methodType;

    @Column(name = "status", nullable = false, length = 30)
    private String status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(String uuid,
                   BigDecimal originalAmount,
                   BigDecimal finalAmount,
                   BigDecimal cashbackAmount,
                   BigDecimal appliedDiscount,
                   String methodType,
                   String status) {

        this.uuid = uuid;
        this.originalAmount = originalAmount;
        this.finalAmount = finalAmount;
        this.cashbackAmount = cashbackAmount;
        this.appliedDiscount = appliedDiscount;
        this.methodType = methodType;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public BigDecimal getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(BigDecimal finalAmount) {
        this.finalAmount = finalAmount;
    }

    public BigDecimal getCashbackAmount() {
        return cashbackAmount;
    }

    public void setCashbackAmount(BigDecimal cashbackAmount) {
        this.cashbackAmount = cashbackAmount;
    }

    public BigDecimal getAppliedDiscount() {
        return appliedDiscount;
    }

    public void setAppliedDiscount(BigDecimal appliedDiscount) {
        this.appliedDiscount = appliedDiscount;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.toString();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public enum PaymentMethodType {
        CREDIT_CARD,
        DEBIT_CARD,
        PIX,
    }
    public enum PaymentStatus {
        CREATED,
        PROCESSING,
        APPROVED,
        FAILED,
        CANCELED
    }
}
