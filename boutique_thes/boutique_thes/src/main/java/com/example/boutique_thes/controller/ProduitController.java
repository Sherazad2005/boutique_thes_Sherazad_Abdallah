package com.example.boutique_thes.controller;

import com.example.boutique_thes.model.Produit;
import com.example.boutique_thes.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProduitController {

    private final ProduitService service ;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("produits", service.findAll());
        return "index";
    }

    @GetMapping("/nouveau")
    public String nouveau(Model model) {
        model.addAttribute("produits", service.findAll());
        return "nouveau";
    }

    @GetMapping("/enregistrer")
    public String enregistrer(@ModelAttribute Produit produit) {
        service.save(produit);
        return "redirect:/";
    }

    @GetMapping("/modifier/{id}")
    public String modifier(@PathVariable Long id, @ModelAttribute Produit produit) {
        produit.setId(id);
        service.save(produit);
        return "redirect:/";
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/";
    }



}
