package com.fitriarien.inventory.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitriarien.inventory.config.JwtTokenUtil;
import com.fitriarien.inventory.entity.Product;
import com.fitriarien.inventory.entity.User;
import com.fitriarien.inventory.model.CreateProductRequest;
import com.fitriarien.inventory.model.ProductResponse;
import com.fitriarien.inventory.model.UpdateProductRequest;
import com.fitriarien.inventory.model.WebResponse;
import com.fitriarien.inventory.repository.ProductRepository;
import com.fitriarien.inventory.repository.UserRepository;
import com.fitriarien.inventory.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "jwt.secret=fitriarien")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("admin1");
        user.setPassword(passwordEncoder.encode("rahasia"));
        user.setName("Admin 1");
        userRepository.save(user);
    }

    @Test
    @WithMockUser(username = "admin1")
    void createProductBadRequest() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        CreateProductRequest request = new CreateProductRequest();
        request.setProductName("");

        mockMvc.perform(
                post("/api/products/users/admin1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void createProductDuplicate() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Laptop A");
        product.setImagePath("www.example.com");
        product.setSellingPrice(15000000);
        product.setStock(10);
        product.setUser(user);
        productRepository.save(product);

        CreateProductRequest request = new CreateProductRequest();
        request.setProductName("Laptop A");

        mockMvc.perform(
                post("/api/products/users/admin1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isImUsed()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void createProductSuccess() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        CreateProductRequest request = new CreateProductRequest();
        request.setProductName("Handphone A");
        request.setImagePath("www.example2.com");
        request.setSellingPrice(12000000);
        request.setStock(10);

        mockMvc.perform(
                post("/api/products/users/admin1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(request.getProductName(), response.getData().getProductName());
            assertEquals(request.getImagePath(), response.getData().getImagePath());
            assertEquals(request.getSellingPrice(), response.getData().getSellingPrice());
            assertEquals(request.getStock(), response.getData().getStock());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void getProductNotFound() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        mockMvc.perform(
                get("/api/products/wrongId")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void getProductSuccess() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Laptop A");
        product.setImagePath("www.example.com");
        product.setSellingPrice(15000000);
        product.setStock(10);
        product.setUser(user);
        productRepository.save(product);

        mockMvc.perform(
                get("/api/products/" + product.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(product.getId(), response.getData().getId());
            assertEquals(product.getProductName(), response.getData().getProductName());
            assertEquals(product.getImagePath(), response.getData().getImagePath());
            assertEquals(product.getSellingPrice(), response.getData().getSellingPrice());
            assertEquals(product.getStock(), response.getData().getStock());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void getAllProductsEmpty() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        mockMvc.perform(
                get("/api/products/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isNoContent()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void getAllProductsSuccess() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setId(UUID.randomUUID().toString());
            product.setProductName("Laptop " + i);
            product.setImagePath("www.example" + i + ".com");
            product.setSellingPrice(15000000 + i*100000);
            product.setStock(10);
            product.setUser(user);
            productRepository.save(product);
        }

        mockMvc.perform(
                get("/api/products/list")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ProductResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(10, response.getData().size());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void updateProductBadRequest() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Laptop A");
        product.setImagePath("www.example.com");
        product.setSellingPrice(15000000);
        product.setStock(10);
        product.setUser(user);
        productRepository.save(product);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductName("");

        mockMvc.perform(
                put("/api/products/wrongId")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void updateProductNotFound() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Laptop A");
        product.setImagePath("www.example.com");
        product.setSellingPrice(15000000);
        product.setStock(10);
        product.setUser(user);
        productRepository.save(product);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductName("Laptop B");
        request.setImagePath("www.example2.com");
        request.setSellingPrice(20000000D);
        request.setStock(5L);

        mockMvc.perform(
                put("/api/products/wrongId")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void updateProductSuccess() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Laptop A");
        product.setImagePath("www.example.com");
        product.setSellingPrice(15000000);
        product.setStock(10);
        product.setUser(user);
        productRepository.save(product);

        UpdateProductRequest request = new UpdateProductRequest();
        request.setProductName("Laptop B");
        request.setImagePath("www.example2.com");
        request.setSellingPrice(20000000D);
        request.setStock(15L);

        mockMvc.perform(
                put("/api/products/" + product.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ProductResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(request.getProductName(), response.getData().getProductName());
            assertEquals(request.getImagePath(), response.getData().getImagePath());
            assertEquals(request.getSellingPrice(), response.getData().getSellingPrice());
            assertEquals(request.getStock(), response.getData().getStock());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void deleteProductNotFound() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        mockMvc.perform(
                delete("/api/products/wrongId")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    @WithMockUser(username = "admin1")
    void deleteProductSuccess() throws Exception {
        UserDetails userDetails = authService.loadUserByUsername("admin1");
        String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByUsername("admin1");
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setProductName("Laptop A");
        product.setImagePath("www.example.com");
        product.setSellingPrice(15000000);
        product.setStock(10);
        product.setUser(user);
        productRepository.save(product);

        mockMvc.perform(
                delete("/api/products/" + product.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        ).andExpect(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("Deleted", response.getData());
        });
    }


}