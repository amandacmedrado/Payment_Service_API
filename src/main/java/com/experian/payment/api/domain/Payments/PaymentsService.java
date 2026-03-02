package com.experian.payment.api.domain.Payments;

import com.experian.payment.api.domain.Payments.dto.PaymentRequest;
import com.experian.payment.api.domain.Payments.dto.PaymentResponse;
import com.experian.payment.api.domain.Produts.Product;
import com.experian.payment.api.domain.Produts.ProductRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class PaymentsService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponse processPayments(PaymentRequest payments) {
        var products = productRepository.findAll();

        if (products.isEmpty()) {
            throw new ValidationException("Não há produtos no estoque!");
        }

        var methodType = payments.getPaymentMethodType();
        var uuidPayment = UUID.randomUUID().toString();
        var status = "PROCESSED";
        var createdAt = LocalDateTime.now();

        BigDecimal originalAmount = BigDecimal.ZERO;
        BigDecimal appliedDiscount = BigDecimal.ZERO;
        BigDecimal finalAmount = BigDecimal.ZERO;
        BigDecimal cashbackAmount = BigDecimal.ZERO;

        Map<Long, Integer> productsIdQuantity = new HashMap<>();

        for (PaymentRequest.Product item : payments.getProducts()) {
            productsIdQuantity.put(item.getProductId(), item.getQuantity());
        }

        for (Product product : products) {
            if (productsIdQuantity.containsKey(product.getUuid())) {
                var quantity = productsIdQuantity.get(product.getUuid());
                originalAmount = originalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            }
        }

        if (methodType.equals(PaymentRequest.PaymentMethodType.PIX.toString())){
            appliedDiscount = originalAmount.multiply(BigDecimal.valueOf(0.05));
            finalAmount = originalAmount.subtract(appliedDiscount);
        }

        if (methodType.equals(PaymentRequest.PaymentMethodType.CREDIT_CARD.toString())){
            finalAmount = originalAmount;
            cashbackAmount = originalAmount.multiply(BigDecimal.valueOf(0.03));
        }

        if (methodType.equals(PaymentRequest.PaymentMethodType.DEBIT_CARD.toString())){
            finalAmount = originalAmount;
        }

        var payment = new Payment(uuidPayment, originalAmount, finalAmount, cashbackAmount, appliedDiscount, methodType, status);
        paymentRepository.save(payment);

        return new PaymentResponse(uuidPayment, originalAmount, appliedDiscount, finalAmount, cashbackAmount,  methodType, status, createdAt);
    }
}

