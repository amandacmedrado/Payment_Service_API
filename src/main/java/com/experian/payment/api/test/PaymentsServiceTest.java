package com.experian.payment.api.test;

import com.experian.payment.api.domain.Payments.PaymentRepository;
import com.experian.payment.api.domain.Payments.PaymentsService;
import com.experian.payment.api.domain.Payments.dto.PaymentRequest;
import com.experian.payment.api.domain.Products.Product;
import com.experian.payment.api.domain.Products.ProductRepository;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class PaymentsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentsService paymentsService;

    @Test
    void shouldApplyPixDiscountCorrectly() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Notebook");
        product.setPrice(new BigDecimal("1000.00"));

        when(productRepository.findAll()).thenReturn(List.of(product));

        PaymentRequest.Product productRequest =
                new PaymentRequest.Product(1L, 1);

        PaymentRequest request =
                new PaymentRequest(
                        List.of(productRequest),
                        PaymentRequest.PaymentMethodType.PIX
                );

        var response = paymentsService.processPayments(request);

        assertEquals(new BigDecimal("1000.00"), response.getOriginalAmount());
        assertEquals(new BigDecimal("50.00"), response.getAppliedDiscount());
        assertEquals(new BigDecimal("950.00"), response.getFinalAmount());
    }
    @Test
    void shouldApplyCreditCardCashback() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Notebook");
        product.setPrice(new BigDecimal("1000.00"));

        when(productRepository.findAll()).thenReturn(List.of(product));

        PaymentRequest.Product productRequest =
                new PaymentRequest.Product(1L, 1);

        PaymentRequest request =
                new PaymentRequest(
                        List.of(productRequest),
                        PaymentRequest.PaymentMethodType.CREDIT_CARD
                );

        var response = paymentsService.processPayments(request);

        assertEquals(new BigDecimal("1000.00"), response.getOriginalAmount());
        assertEquals(new BigDecimal("30.00"), response.getCashbackAmount());
        assertEquals(new BigDecimal("1000.00"), response.getFinalAmount());
    }
    @Test
    void shouldProcessDebitCardWithoutDiscount() {

        Product product = new Product();
        product.setId(1L);
        product.setName("Notebook");
        product.setPrice(new BigDecimal("1000.00"));

        when(productRepository.findAll()).thenReturn(List.of(product));

        PaymentRequest.Product productRequest =
                new PaymentRequest.Product(1L, 1);

        PaymentRequest request =
                new PaymentRequest(
                        List.of(productRequest),
                        PaymentRequest.PaymentMethodType.DEBIT_CARD
                );

        var response = paymentsService.processPayments(request);

        assertEquals(new BigDecimal("1000.00"), response.getOriginalAmount());
        assertEquals(new BigDecimal("1000.00"), response.getFinalAmount());
    }
    @Test
    void shouldThrowExceptionWhenNoProductsInStock() {

        when(productRepository.findAll()).thenReturn(List.of());

        PaymentRequest.Product productRequest =
                new PaymentRequest.Product(1L, 1);

        PaymentRequest request =
                new PaymentRequest(
                        List.of(productRequest),
                        PaymentRequest.PaymentMethodType.PIX
                );

        assertThrows(ValidationException.class, () -> {
            paymentsService.processPayments(request);
        });
    }
}
