package com.fitriarien.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TokenResponse {

    private String id;

    private String username;

    private String name;

    private String token;
}
