package com.example.boutique_thes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nom;
    private String origine;
    private float prix;
    private int quantiteStock;
    private String description;
    private LocalDateTime dateReception;
    private String typeThe;

    public Produit() {}
    public Produit(Long id, String nom, String origine, float prix, int quantiteStock, String description, LocalDateTime dateReception, String typeThe) {

        this.id = id;
        this.nom = nom;
        this.origine = origine;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.description = description;
        this.dateReception = dateReception;
        this.typeThe = typeThe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateReception() {
        return dateReception;
    }

    public void setDateReception(LocalDateTime dateReception) {
        this.dateReception = dateReception;
    }

    public String getTypeThe() {
        return typeThe;
    }

    public void setTypeThe(String typeThe) {
        this.typeThe = typeThe;
    }
}
