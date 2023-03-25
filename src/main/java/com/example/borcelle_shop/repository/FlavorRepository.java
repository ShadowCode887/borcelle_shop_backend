package com.example.borcelle_shop.repository;

import com.example.borcelle_shop.model.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlavorRepository extends JpaRepository<Flavor, Long> {
}
