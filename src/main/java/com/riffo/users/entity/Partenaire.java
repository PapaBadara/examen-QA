package com.riffo.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "partenaires")
public class Partenaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nom;

    @NotBlank(message = "La categorie est obligatoire")
    @Pattern(regexp = "^(PARTICULIER|PROFESSIONNEL|ASSOCIATION)$",
            message = "Categorie doit etre PARTICULIER, PROFESSIONNEL ou ASSOCIATION")
    @Column(nullable = false, length = 50)
    private String categorie;

    @NotBlank(message = "L'adresse est obligatoire")
    @Column(nullable = false, length = 255)
    private String adresse;

    @NotBlank(message = "La ville est obligatoire")
    @Column(nullable = false, length = 50)
    private String ville;

    @NotBlank(message = "Le telephone est obligatoire")
    @Pattern(regexp = "^(\\+221)?[7][0-9]{8}$",
            message = "Format telephone invalide (ex: 771234567 ou +221771234567)")
    @Column(nullable = false, length = 20)
    private String telephone;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format email invalide")
    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @NotNull(message = "La latitude est obligatoire")
    @DecimalMin(value = "-90.0", message = "Latitude doit etre >= -90")
    @DecimalMax(value = "90.0", message = "Latitude doit etre <= 90")
    @Column(nullable = false)
    private Double latitude;

    @NotNull(message = "La longitude est obligatoire")
    @DecimalMin(value = "-180.0", message = "Longitude doit etre >= -180")
    @DecimalMax(value = "180.0", message = "Longitude doit etre <= 180")
    @Column(nullable = false)
    private Double longitude;

    @NotBlank(message = "Le statut est obligatoire")
    @Pattern(regexp = "^(ACTIF|INACTIF|SUSPENDU)$",
            message = "Statut doit etre ACTIF, INACTIF ou SUSPENDU")
    @Column(nullable = false, length = 20)
    private String statut;

    @NotNull(message = "Le plafond est obligatoire")
    @Positive(message = "Le plafond doit etre positif")
    @Column(nullable = false)
    private Double plafondPriseEnCharge;

    /**
     * Constructeur par defaut.
     */
    public Partenaire() {
        // Constructeur vide requis par JPA
    }

    /**
     * Constructeur avec tous les champs.
     *
     * @param nom nom du partenaire
     * @param categorie categorie du partenaire
     * @param adresse adresse postale
     * @param ville ville d'implantation
     * @param telephone numero de telephone
     * @param email adresse email
     * @param latitude coordonnee geographique
     * @param longitude coordonnee geographique
     * @param statut statut du partenaire
     * @param plafondPriseEnCharge plafond financier
     */
    public Partenaire(String nom, String categorie, String adresse, String ville,
                      String telephone, String email, Double latitude, Double longitude,
                      String statut, Double plafondPriseEnCharge) {
        this.nom = nom;
        this.categorie = categorie;
        this.adresse = adresse;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
        this.statut = statut;
        this.plafondPriseEnCharge = plafondPriseEnCharge;
    }

    /**
     * Retourne l'identifiant du partenaire.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifie l'identifiant du partenaire.
     *
     * @param id nouvel identifiant
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom du partenaire.
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Modifie le nom du partenaire.
     *
     * @param nom nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la categorie du partenaire.
     *
     * @return categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Modifie la categorie du partenaire.
     *
     * @param categorie nouvelle categorie
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * Retourne l'adresse du partenaire.
     *
     * @return adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Modifie l'adresse du partenaire.
     *
     * @param adresse nouvelle adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Retourne la ville du partenaire.
     *
     * @return ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Modifie la ville du partenaire.
     *
     * @param ville nouvelle ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Retourne le numero de telephone.
     *
     * @return telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Modifie le numero de telephone.
     *
     * @param telephone nouveau numero
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Retourne l'adresse email.
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modifie l'adresse email.
     *
     * @param email nouvelle adresse
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne la latitude.
     *
     * @return latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Modifie la latitude.
     *
     * @param latitude nouvelle latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Retourne la longitude.
     *
     * @return longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Modifie la longitude.
     *
     * @param longitude nouvelle longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Retourne le statut du partenaire.
     *
     * @return statut
     */
    public String getStatut() {
        return statut;
    }

    /**
     * Modifie le statut du partenaire.
     *
     * @param statut nouveau statut
     */
    public void setStatut(String statut) {
        this.statut = statut;
    }

    /**
     * Retourne le plafond de prise en charge.
     *
     * @return plafond
     */
    public Double getPlafondPriseEnCharge() {
        return plafondPriseEnCharge;
    }

    /**
     * Modifie le plafond de prise en charge.
     *
     * @param plafondPriseEnCharge nouveau plafond
     */
    public void setPlafondPriseEnCharge(Double plafondPriseEnCharge) {
        this.plafondPriseEnCharge = plafondPriseEnCharge;
    }
}