package com.fitriarien.inventory.service;

import com.fitriarien.inventory.entity.Product;
import com.fitriarien.inventory.entity.User;
import com.fitriarien.inventory.model.CreateProductRequest;
import com.fitriarien.inventory.model.ProductResponse;
import com.fitriarien.inventory.model.UpdateProductRequest;
import com.fitriarien.inventory.repository.ProductRepository;
import com.fitriarien.inventory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public ProductResponse create(String username, CreateProductRequest request) {
        validationService.validate(request);

        User user = userRepository.findByUsername(username);
        Product product = productRepository.findByProductName(request.getProductName());

        if (product != null) {
            throw new ResponseStatusException(HttpStatus.IM_USED, "Product name has been used.");
        }

        product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setImagePath(request.getImagePath());
        product.setProductName(request.getProductName());
        product.setSellingPrice(request.getSellingPrice());
        product.setStock(request.getStock());
        product.setUser(user);

        productRepository.save(product);
        return toResponse(product);
    }

    private ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .imagePath(product.getImagePath())
                .productName(product.getProductName())
                .sellingPrice(product.getSellingPrice())
                .stock(product.getStock())
                .build();
    }

    @Transactional(readOnly = true)
    public ProductResponse get(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return toResponse(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);

        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data");
        }

        List<ProductResponse> productResponseList = products.getContent().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(productResponseList, pageable, products.getTotalElements());
    }

    @Transactional
    public ProductResponse update(String productId, UpdateProductRequest request) {
        validationService.validate(request);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        if (request.getProductName() != null) {
            product.setProductName(request.getProductName());
        }

        if (request.getImagePath() != null) {
            product.setImagePath(request.getImagePath());
        }

        if (request.getSellingPrice() != null) {
            product.setSellingPrice(request.getSellingPrice());
        }

        if (request.getStock() != null) {
            product.setStock(request.getStock());
        }

        productRepository.save(product);
        return toResponse(product);
    }

    @Transactional
    public void delete(String productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponse> search(String productName, int page, int size) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(productName)) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + productName + "%"));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(specification, pageable);

        List<ProductResponse> productResponseList = products.getContent().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        if (productResponseList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return new PageImpl<>(productResponseList, pageable, products.getTotalElements());
    }
}
