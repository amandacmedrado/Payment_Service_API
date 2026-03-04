package com.experian.payment.api.controller;

import com.experian.payment.api.domain.Products.ProductService;
import com.experian.payment.api.domain.Products.dto.ProductRequest;
import com.experian.payment.api.domain.Products.dto.ProductResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request) {

        ProductResponse response = service.create(request);

        return ResponseEntity
                .created(URI.create("/products/" + response.getUuid()))
                .body(response);
    }

    // LIST
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> list(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {

        return ResponseEntity.ok(service.list(pageable));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ProductResponse> findByUuid(@PathVariable String uuid) {

        return ResponseEntity.ok(service.findByUuid(uuid));
    }

    // UPDATE
    @PutMapping("/{uuid}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable String uuid,
            @RequestBody ProductRequest request) {

        return ResponseEntity.ok(service.update(uuid, request));
    }

    // DELETE
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {

        service.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}