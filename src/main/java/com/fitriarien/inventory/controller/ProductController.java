package com.fitriarien.inventory.controller;

import com.fitriarien.inventory.model.*;
import com.fitriarien.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(
            path = "/api/products/users/{username}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> create(@PathVariable("username") String username, @RequestBody CreateProductRequest request) {
        ProductResponse productResponse = productService.create(username, request);
        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @GetMapping(
            path = "/api/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> get(@PathVariable("productId") String productId) {
        ProductResponse productResponse = productService.get(productId);
        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @GetMapping(
            path = "/api/products/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> list(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<ProductResponse> productResponses = productService.list(page, size);
        return WebResponse.<List<ProductResponse>>builder()
                .data(productResponses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(productResponses.getNumber())
                        .size(productResponses.getSize())
                        .totalPage(productResponses.getTotalPages())
                        .build())
                .build();
    }

    @PutMapping(
            path = "/api/products/{productId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<ProductResponse> update(@PathVariable("productId") String productId,
                                               @RequestBody UpdateProductRequest request) {
        ProductResponse productResponse = productService.update(productId, request);
        return WebResponse.<ProductResponse>builder().data(productResponse).build();
    }

    @DeleteMapping(
            path = "/api/products/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(@PathVariable("productId") String productId) {
        productService.delete(productId);
        return WebResponse.<String>builder().data("Deleted").build();
    }

    @GetMapping(
            path = "/api/products",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<ProductResponse>> search(@RequestParam(value = "productName", required = false) String productName,
                                                     @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<ProductResponse> productResponses = productService.search(productName, page, size);
        return WebResponse.<List<ProductResponse>>builder()
                .data(productResponses.getContent())
                .paging(PagingResponse.builder()
                        .currentPage(productResponses.getNumber())
                        .totalPage(productResponses.getTotalPages())
                        .size(productResponses.getSize())
                        .build())
                .build();
    }
}
