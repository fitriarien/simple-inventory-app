package com.fitriarien.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private String id;

    private String imagePath;

    private String productName;

    private double sellingPrice;

    private long stock;
}
