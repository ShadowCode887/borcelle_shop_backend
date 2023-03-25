package com.example.borcelle_shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private Set<ProductDetails> details = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String name;

    private String slogan;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double rating;

    @ManyToMany
    private Set<ProductCategory> categories;

    private LocalDateTime added = LocalDateTime.now();
}
