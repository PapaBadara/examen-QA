package com.riffo.users.controller;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.service.PartenaireService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des partenaires
 * Base URL: /api/partenaires
 *
 * CORRECTIONS APPORTÉES:
 * 1. Ajout de @Valid pour déclencher les validations des annotations
 * 2. Suppression des blocs try/catch (délégués à GlobalExceptionHandler)
 * 3. Plus de ResponseEntity.notFound() - les exceptions sont propagées
 * 4. Code plus propre et plus concis
 */
@RestController
//@RequestMapping("/api/partenaires")
@RequestMapping("/partenaires")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PartenaireRESTController {

    @Autowired
    private PartenaireService partenaireService;

    /**
     * Récupère tous les partenaires
     * GET /api/partenaires
     * @return liste de tous les partenaires avec code 200
     */
    @GetMapping
    public ResponseEntity<List<Partenaire>> getAllPartenaires() {
        return ResponseEntity.ok(partenaireService.getAllPartenaires());
    }

    /**
     * Récupère un partenaire par son ID
     * GET /api/partenaires/{id}
     * @return le partenaire avec code 200, ou 404 automatiquement via exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<Partenaire> getPartenaireById(@PathVariable Long id) {
        return ResponseEntity.ok(partenaireService.getPartenaireById(id));
    }

    /**
     * Recherche d'un partenaire par nom
     * GET /api/partenaires/search/nom?nom={nom}
     */
    @GetMapping("/search/nom")
    public ResponseEntity<Partenaire> getPartenaireByNom(@RequestParam String nom) {
        return ResponseEntity.ok(partenaireService.getPartenaireByNom(nom));
    }

    /**
     * Recherche des partenaires par catégorie
     * GET /api/partenaires/search/categorie?categorie={categorie}
     */
    @GetMapping("/search/categorie")
    public ResponseEntity<List<Partenaire>> getPartenairesByCategorie(@RequestParam String categorie) {
        return ResponseEntity.ok(partenaireService.getPartenairesByCategorie(categorie));
    }

    /**
     * Recherche des partenaires par statut
     * GET /api/partenaires/search/statut?statut={statut}
     */
    @GetMapping("/search/statut")
    public ResponseEntity<List<Partenaire>> getPartenairesByStatut(@RequestParam String statut) {
        return ResponseEntity.ok(partenaireService.getPartenairesByStatut(statut));
    }

    /**
     * Recherche des partenaires par ville
     * GET /api/partenaires/search/ville?ville={ville}
     */
    @GetMapping("/search/ville")
    public ResponseEntity<List<Partenaire>> getPartenairesByVille(@RequestParam String ville) {
        return ResponseEntity.ok(partenaireService.getPartenairesByVille(ville));
    }

    /**
     * Recherche d'un partenaire par email
     * GET /api/partenaires/search/email?email={email}
     */
    @GetMapping("/search/email")
    public ResponseEntity<Partenaire> getPartenaireByEmail(@RequestParam String email) {
        return ResponseEntity.ok(partenaireService.getPartenaireByEmail(email));
    }

    /**
     * Crée un nouveau partenaire
     * POST /api/partenaires
     *
     * CORRECTION: Ajout de @Valid pour activer les validations
     * CORRECTION: Plus de try/catch - l'exception est gérée globalement
     */
    @PostMapping
    public ResponseEntity<Partenaire> addPartenaire(@Valid @RequestBody Partenaire partenaire) {
        Partenaire nouveau = partenaireService.addPartenaire(partenaire);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveau);
    }

    /**
     * Met à jour un partenaire existant
     * PUT /api/partenaires/{id}
     *
     * CORRECTION: Ajout de @Valid pour les validations
     */
    @PutMapping("/{id}")
    public ResponseEntity<Partenaire> updatePartenaire(@PathVariable Long id,
                                                       @Valid @RequestBody Partenaire partenaire) {
        return ResponseEntity.ok(partenaireService.updatePartenaire(id, partenaire));
    }

    /**
     * Supprime un partenaire
     * DELETE /api/partenaires/{id}
     * @return 204 No Content en cas de succès
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartenaire(@PathVariable Long id) {
        partenaireService.deletePartenaire(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Compte le nombre total de partenaires
     * GET /api/partenaires/count
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countPartenaires() {
        return ResponseEntity.ok(partenaireService.countPartenaires());
    }

    /**
     * Vérifie l'existence par email
     * GET /api/partenaires/exists/email?email={email}
     */
    @GetMapping("/exists/email")
    public ResponseEntity<Boolean> existsByEmail(@RequestParam String email) {
        return ResponseEntity.ok(partenaireService.existsByEmail(email));
    }
}