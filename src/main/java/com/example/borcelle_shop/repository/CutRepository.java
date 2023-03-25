package com.example.borcelle_shop.repository;

import com.example.borcelle_shop.model.Cut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CutRepository extends JpaRepository<Cut, Long> {
}
