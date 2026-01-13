package com.example.boutique_thes.service;

import com.example.boutique_thes.model.Produit;
import com.example.boutique_thes.repository.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository repo;

    public ProduitServiceImpl(ProduitRepository repo) {
        this.repo = repo;
    }

    @Override
    public Page<Produit> search(String q, String type, Pageable pageable) {
        String query = (q == null) ? "" : q.trim();
        String t = (type == null) ? "" : type.trim();
        return repo.findByNomContainingIgnoreCaseAndTypeTheContainingIgnoreCase(query, t, pageable);
    }

    @Override
    public List<Produit> findAll() {
        return repo.findAll();
    }

    @Override
    public Produit save(Produit produit) {
        return repo.save(produit);
    }

    @Override
    public Produit getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}

