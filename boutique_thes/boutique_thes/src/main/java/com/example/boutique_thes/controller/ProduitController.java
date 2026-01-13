package com.example.boutique_thes.controller;

import com.example.boutique_thes.model.Produit;
import com.example.boutique_thes.service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;

@Controller
public class ProduitController {

    private final ProduitService service;

    public ProduitController(ProduitService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "nom") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            Model model
    ) {

        String sortField = switch (sort) {
            case "prix" -> "prix";
            case "quantiteStock" -> "quantiteStock";
            default -> "nom";
        };

        Sort.Direction direction = dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, 10, Sort.by(direction, sortField));

        Page<Produit> produitsPage = service.search(q, type, pageable);

        model.addAttribute("produitsPage", produitsPage);
        model.addAttribute("produits", produitsPage.getContent()); // simple pour la vue
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("type", type == null ? "" : type);
        model.addAttribute("sort", sortField);
        model.addAttribute("dir", dir);

        return "index";
    }



    @GetMapping("/nouveau")
    public String nouveau(Model model) {
        model.addAttribute("produit", new Produit());
        return "formulaire-produit";
    }


    @PostMapping("/enregistrer")
    public String enregistrer(@ModelAttribute Produit produit) {
        service.save(produit);
        return "redirect:/";
    }

    @GetMapping("/modifier/{id}")
    public String modifierForm(@PathVariable Long id, Model model) {
        model.addAttribute("produit", service.getById(id));
        return "formulaire-produit";
    }

    @PostMapping("/modifier/{id}")
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

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportCsv(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "nom") String sort,
            @RequestParam(defaultValue = "asc") String dir
    ) {
        // on exporte tout (sans pagination) en appliquant tri/recherche/filtre
        String sortField = switch (sort) {
            case "prix" -> "prix";
            case "quantiteStock" -> "quantiteStock";
            default -> "nom";
        };
        Sort.Direction direction = dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE, Sort.by(direction, sortField));

        var pageAll = service.search(q, type, pageable);

        StringBuilder sb = new StringBuilder();
        sb.append("id,nom,typeThe,origine,prix,quantiteStock,description,dateReception\n");
        for (Produit p : pageAll.getContent()) {
            sb.append(p.getId()).append(",");
            sb.append(escape(p.getNom())).append(",");
            sb.append(escape(p.getTypeThe())).append(",");
            sb.append(escape(p.getOrigine())).append(",");
            sb.append(p.getPrix()).append(",");
            sb.append(p.getQuantiteStock()).append(",");
            sb.append(escape(p.getDescription())).append(",");
            sb.append(p.getDateReception()).append("\n");
        }

        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=produits.csv")
                .header(HttpHeaders.CONTENT_TYPE, "text/csv; charset=UTF-8")
                .body(bytes);
    }

    private String escape(String s) {
        if (s == null) return "";
        // CSV simple : on remplace les retours ligne et on quote si besoin
        String cleaned = s.replace("\n", " ").replace("\r", " ");
        if (cleaned.contains(",") || cleaned.contains("\"")) {
            cleaned = cleaned.replace("\"", "\"\"");
            return "\"" + cleaned + "\"";
        }
        return cleaned;
    }

}

