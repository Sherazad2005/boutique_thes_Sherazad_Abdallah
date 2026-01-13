package com.example.boutique_thes.repository;

import com.example.boutique_thes.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByName(String nom);

    List<Produit> findByOrigine(String otigine);
}
