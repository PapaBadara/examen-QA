package com.riffo.users.service.impl;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.exception.DuplicateResourceException;
import com.riffo.users.exception.ResourceNotFoundException;
import com.riffo.users.repository.PartenaireRepository;
import com.riffo.users.service.PartenaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implémentation du service de gestion des partenaires
 *
 * CORRECTIONS APPORTÉES:
 * 1. Utilisation de BeanUtils.copyProperties pour remplacer les 10+ vérifications null
 * 2. Exceptions métier spécifiques au lieu de IllegalArgumentException générique
 * 3. Meilleure gestion des cas d'erreur (404, 409)
 * 4. Code plus concis et maintenable (principe DRY)
 */
@Service
@Transactional
public class PartenaireServiceImpl implements PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getAllPartenaires() {
        return partenaireRepository.findAll();
    }

    /**
     * Récupération d'un partenaire par ID
     * CORRECTION: Lance ResourceNotFoundException au lieu de retourner Optional
     */
    @Override
    @Transactional(readOnly = true)
    public Partenaire getPartenaireById(Long id) {
        return partenaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouve avec id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Partenaire getPartenaireByNom(String nom) {
        return partenaireRepository.findByNom(nom)
                .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouve avec nom: " + nom));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getPartenairesByCategorie(String categorie) {
        return partenaireRepository.findByCategorie(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getPartenairesByStatut(String statut) {
        return partenaireRepository.findByStatut(statut);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Partenaire> getPartenairesByVille(String ville) {
        return partenaireRepository.findByVille(ville);
    }

    @Override
    @Transactional(readOnly = true)
    public Partenaire getPartenaireByEmail(String email) {
        return partenaireRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouve avec email: " + email));
    }

    /**
     * Création d'un nouveau partenaire
     * CORRECTION: Vérification d'unicité email avec exception spécifique
     */
    @Override
    public Partenaire addPartenaire(Partenaire partenaire) {
        if (existsByEmail(partenaire.getEmail())) {
            throw new DuplicateResourceException("Un partenaire avec l'email " + partenaire.getEmail() + " existe deja");
        }
        return partenaireRepository.save(partenaire);
    }

    /**
     * Mise à jour d'un partenaire existant
     * CORRECTION MAJEURE: Remplacé les 11 vérifications null par BeanUtils.copyProperties
     * Le code est passé de ~50 lignes à ~15 lignes
     */
    @Override
    public Partenaire updatePartenaire(Long id, Partenaire partenaire) {
        Partenaire existing = getPartenaireById(id);

        // Vérifier si nouvel email déjà utilisé par un autre partenaire
        if (!existing.getEmail().equals(partenaire.getEmail()) && existsByEmail(partenaire.getEmail())) {
            throw new DuplicateResourceException("Email " + partenaire.getEmail() + " deja utilise");
        }

        // Copie automatique des propriétés (ignore "id" pour ne pas modifier la clé primaire)
        BeanUtils.copyProperties(partenaire, existing, "id");
        return partenaireRepository.save(existing);
    }

    @Override
    public void deletePartenaire(Long id) {
        if (!partenaireRepository.existsById(id)) {
            throw new ResourceNotFoundException("Partenaire non trouve avec id: " + id);
        }
        partenaireRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countPartenaires() {
        return partenaireRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return partenaireRepository.findByEmail(email).isPresent();
    }
}