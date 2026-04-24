package com.riffo.users.service;

import com.riffo.users.entity.Partenaire;
import java.util.List;

/**
 * Interface service pour la gestion des partenaires
 */
public interface PartenaireService {

    /**
     * Récupère tous les partenaires
     * @return liste de tous les partenaires
     */
    List<Partenaire> getAllPartenaires();

    /**
     * Récupère un partenaire par son ID
     * @param id l'ID du partenaire
     * @return le partenaire (exception si non trouvé)
     */
    Partenaire getPartenaireById(Long id);

    /**
     * Récupère un partenaire par son nom
     * @param nom le nom du partenaire
     * @return le partenaire (exception si non trouvé)
     */
    Partenaire getPartenaireByNom(String nom);

    /**
     * Récupère tous les partenaires d'une catégorie
     * @param categorie la catégorie
     * @return liste des partenaires de la catégorie
     */
    List<Partenaire> getPartenairesByCategorie(String categorie);

    /**
     * Récupère tous les partenaires d'un statut
     * @param statut le statut
     * @return liste des partenaires du statut
     */
    List<Partenaire> getPartenairesByStatut(String statut);

    /**
     * Récupère tous les partenaires d'une ville
     * @param ville la ville
     * @return liste des partenaires de la ville
     */
    List<Partenaire> getPartenairesByVille(String ville);

    /**
     * Récupère un partenaire par son email
     * @param email l'email du partenaire
     * @return le partenaire (exception si non trouvé)
     */
    Partenaire getPartenaireByEmail(String email);

    /**
     * Ajoute un nouveau partenaire
     * @param partenaire le partenaire à ajouter
     * @return le partenaire enregistré
     */
    Partenaire addPartenaire(Partenaire partenaire);

    /**
     * Met à jour un partenaire existant
     * @param id l'ID du partenaire
     * @param partenaire les données mises à jour
     * @return le partenaire mis à jour
     */
    Partenaire updatePartenaire(Long id, Partenaire partenaire);

    /**
     * Supprime un partenaire
     * @param id l'ID du partenaire à supprimer
     */
    void deletePartenaire(Long id);

    /**
     * Compte le nombre total de partenaires
     * @return le nombre de partenaires
     */
    long countPartenaires();

    /**
     * Vérifie si un partenaire existe par son email
     * @param email l'email du partenaire
     * @return true si le partenaire existe, false sinon
     */
    boolean existsByEmail(String email);
}