package com.example.boutique_thes.service;

import com.example.boutique_thes.model.Produit;

import java.util.List;

public interface ProduitService {

    List<Produit> findAll();
    Produit findById(int id);
    void save(Produit produit);
    void update(Produit produit);
    void delete(int id);
    
}
