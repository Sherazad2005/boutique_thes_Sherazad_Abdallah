package com.example.boutique_thes.service;

import com.example.boutique_thes.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProduitService {

    Page<Produit> search(String q, String type, Pageable pageable);
    List<Produit> findAll();

    Produit save(Produit produit);

    Produit getById(Long id);

    void delete(Long id);
}
