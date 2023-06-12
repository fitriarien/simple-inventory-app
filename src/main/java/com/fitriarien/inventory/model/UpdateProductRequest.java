package com.fitriarien.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {

    @Size(max = 300)
    private String imagePath;

    @NotBlank
    @Size(max = 100)
    private String productName;

    private Double sellingPrice;

    private Long stock;
}
