package com.example.borcelle_shop.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    @OneToOne
    private Address address;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();
}
