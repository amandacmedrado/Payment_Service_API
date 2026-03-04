package com.experian.payment.api.domain.Products;

import com.experian.payment.api.domain.Products.dto.ProductRequest;
import com.experian.payment.api.domain.Products.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse create(ProductRequest request) {

        Product product = new Product(
                UUID.randomUUID().toString(),
                request.name(),
                request.price()
        );

        repository.save(product);

        return new ProductResponse(product);
    }

    public Page<ProductResponse> list(Pageable pageable) {
        return repository.findAll(pageable)
                .map(ProductResponse::new);
    }

    public ProductResponse findByUuid(String uuid) {

        Product product = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProductResponse(product);
    }

    public ProductResponse update(String uuid, ProductRequest request) {

        Product product = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setName(request.name());
        product.setPrice(request.price());

        repository.save(product);

        return new ProductResponse(product);
    }

    public void delete(String uuid) {

        Product product = repository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        repository.delete(product);
    }
}


