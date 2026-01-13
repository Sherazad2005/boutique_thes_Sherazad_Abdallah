package com.example.boutique_thes.service;

import com.example.boutique_thes.model.Produit;

import java.util.List;

public interface ProduitService {

    List<Produit> findAll();

    Produit save(Produit produit);

    Produit getById(Long id);

    void delete(Long id);
}
