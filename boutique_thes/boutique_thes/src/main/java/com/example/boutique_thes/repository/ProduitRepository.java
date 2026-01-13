package com.example.boutique_thes.repository;

import com.example.boutique_thes.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
