package com.experian.payment.api.controller;

import com.experian.payment.api.domain.Payments.PaymentsService;
import com.experian.payment.api.domain.Payments.dto.PaymentRequest;
import com.experian.payment.api.domain.Payments.dto.PaymentResponse;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping({"payments"})
public class PaymentController {
    @Autowired
    private PaymentsService service;

    public PaymentController() {
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> processPayments(@RequestBody @Valid PaymentRequest payments, UriComponentsBuilder uriBuilder) {
        PaymentResponse result = this.service.processPayments(payments);
        URI uri = uriBuilder.path("/payments/{id}").buildAndExpand(new Object[]{result.getId()}).toUri();
        return ResponseEntity.created(uri).body(result);
    }
}
