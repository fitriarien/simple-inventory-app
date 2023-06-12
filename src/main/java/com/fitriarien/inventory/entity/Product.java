package com.fitriarien.inventory.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    @Column(name = "image")
    private String imagePath;

    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private double sellingPrice;

    @Column(name = "stock")
    private long stock;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
