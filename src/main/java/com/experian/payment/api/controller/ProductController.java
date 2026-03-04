package com.experian.payment.api.controller;

import com.experian.payment.api.domain.Produts.ProductRepository;
import com.experian.payment.api.domain.Produts.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/products"})
public class ProductController {
    @Autowired
    private ProductRepository repository;


    @GetMapping
    public ResponseEntity<Page<ProductResponse>> list(@PageableDefault(size = 10,sort = {"name"}) Pageable paginacao) {
        Page<ProductResponse> products = this.repository.findAll(paginacao).map(ProductResponse::new);
        return ResponseEntity.ok(products);
    }
}

