package com.riffo.users.service;

import com.riffo.users.entity.Partenaire;
import com.riffo.users.exception.DuplicateResourceException;
import com.riffo.users.exception.ResourceNotFoundException;
import com.riffo.users.repository.PartenaireRepository;
import com.riffo.users.service.impl.PartenaireServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitaires du service PartenaireServiceImpl")
class PartenaireServiceImplTest {

    @Mock
    private PartenaireRepository partenaireRepository;

    @InjectMocks
    private PartenaireServiceImpl partenaireService;

    private Partenaire partenaire;
    private Partenaire autrePartenaire;

    @BeforeEach
    void setUp() {
        partenaire = new Partenaire();
        partenaire.setId(1L);
        partenaire.setNom("Assurance SUNU");
        partenaire.setCategorie("PROFESSIONNEL");
        partenaire.setAdresse("123 Rue de la Paix");
        partenaire.setVille("Dakar");
        partenaire.setTelephone("771234567");
        partenaire.setEmail("contact@sunu.sn");
        partenaire.setLatitude(14.7167);
        partenaire.setLongitude(-17.4677);
        partenaire.setStatut("ACTIF");
        partenaire.setPlafondPriseEnCharge(1000000.0);

        autrePartenaire = new Partenaire();
        autrePartenaire.setId(2L);
        autrePartenaire.setNom("AXA Assurance");
        autrePartenaire.setCategorie("PROFESSIONNEL");
        autrePartenaire.setAdresse("45 Avenue Cheikh Anta Diop");
        autrePartenaire.setVille("Dakar");
        autrePartenaire.setTelephone("778887766");
        autrePartenaire.setEmail("contact@axa.sn");
        autrePartenaire.setLatitude(14.7167);
        autrePartenaire.setLongitude(-17.4677);
        autrePartenaire.setStatut("ACTIF");
        autrePartenaire.setPlafondPriseEnCharge(2000000.0);
    }

    // ==================== TEST CREATION ====================

    @Test
    @DisplayName("Creation d'un partenaire - Succes")
    void addPartenaire_WithValidData_ShouldSaveAndReturnPartenaire() {
        when(partenaireRepository.findByEmail(partenaire.getEmail())).thenReturn(Optional.empty());
        when(partenaireRepository.save(any(Partenaire.class))).thenReturn(partenaire);

        Partenaire result = partenaireService.addPartenaire(partenaire);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Assurance SUNU");

        verify(partenaireRepository).findByEmail(partenaire.getEmail());
        verify(partenaireRepository).save(any(Partenaire.class));
    }

    @Test
    @DisplayName("Creation d'un partenaire - Email deja existant (Exception)")
    void addPartenaire_WithExistingEmail_ShouldThrowDuplicateResourceException() {
        when(partenaireRepository.findByEmail(partenaire.getEmail())).thenReturn(Optional.of(partenaire));

        assertThatThrownBy(() -> partenaireService.addPartenaire(partenaire))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("existe deja");

        verify(partenaireRepository, never()).save(any(Partenaire.class));
    }

    // ==================== TEST RECHERCHE PAR ID ====================

    @Test
    @DisplayName("Recherche par ID - Partenaire existe")
    void getPartenaireById_WhenExists_ShouldReturnPartenaire() {
        when(partenaireRepository.findById(1L)).thenReturn(Optional.of(partenaire));

        Partenaire result = partenaireService.getPartenaireById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getNom()).isEqualTo("Assurance SUNU");
        verify(partenaireRepository).findById(1L);
    }

    @Test
    @DisplayName("Recherche par ID - Partenaire inexistant (Exception 404)")
    void getPartenaireById_WhenNotExists_ShouldThrowResourceNotFoundException() {
        when(partenaireRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> partenaireService.getPartenaireById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("non trouve avec id: 99");
    }

    // ==================== TEST RECHERCHE PAR NOM ====================

    @Test
    @DisplayName("Recherche par nom - Partenaire existe")
    void getPartenaireByNom_WhenExists_ShouldReturnPartenaire() {
        when(partenaireRepository.findByNom("Assurance SUNU")).thenReturn(Optional.of(partenaire));

        Partenaire result = partenaireService.getPartenaireByNom("Assurance SUNU");

        assertThat(result).isNotNull();
        assertThat(result.getNom()).isEqualTo("Assurance SUNU");
        verify(partenaireRepository).findByNom("Assurance SUNU");
    }

    @Test
    @DisplayName("Recherche par nom - Partenaire inexistant (Exception)")
    void getPartenaireByNom_WhenNotExists_ShouldThrowResourceNotFoundException() {
        when(partenaireRepository.findByNom("Inexistant")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> partenaireService.getPartenaireByNom("Inexistant"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("non trouve avec nom: Inexistant");
    }

    // ==================== TEST RECHERCHE PAR EMAIL ====================

    @Test
    @DisplayName("Recherche par email - Partenaire existe")
    void getPartenaireByEmail_WhenExists_ShouldReturnPartenaire() {
        when(partenaireRepository.findByEmail("contact@sunu.sn")).thenReturn(Optional.of(partenaire));

        Partenaire result = partenaireService.getPartenaireByEmail("contact@sunu.sn");

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo("contact@sunu.sn");
        verify(partenaireRepository).findByEmail("contact@sunu.sn");
    }

    @Test
    @DisplayName("Recherche par email - Partenaire inexistant (Exception)")
    void getPartenaireByEmail_WhenNotExists_ShouldThrowResourceNotFoundException() {
        when(partenaireRepository.findByEmail("inexistant@test.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> partenaireService.getPartenaireByEmail("inexistant@test.com"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("non trouve avec email: inexistant@test.com");
    }

    // ==================== TEST LISTES ====================

    @Test
    @DisplayName("Recuperation de tous les partenaires")
    void getAllPartenaires_ShouldReturnListOfPartenaires() {
        List<Partenaire> partenaires = Arrays.asList(partenaire, autrePartenaire);
        when(partenaireRepository.findAll()).thenReturn(partenaires);

        List<Partenaire> result = partenaireService.getAllPartenaires();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getNom()).isEqualTo("Assurance SUNU");
        assertThat(result.get(1).getNom()).isEqualTo("AXA Assurance");
        verify(partenaireRepository).findAll();
    }

    // ==================== TEST SUPPRESSION ====================

    @Test
    @DisplayName("Suppression d'un partenaire - Succes")
    void deletePartenaire_WhenExists_ShouldDelete() {
        when(partenaireRepository.existsById(1L)).thenReturn(true);
        doNothing().when(partenaireRepository).deleteById(1L);

        partenaireService.deletePartenaire(1L);

        verify(partenaireRepository).existsById(1L);
        verify(partenaireRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Suppression d'un partenaire - Partenaire inexistant (Exception)")
    void deletePartenaire_WhenNotExists_ShouldThrowResourceNotFoundException() {
        when(partenaireRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> partenaireService.deletePartenaire(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("non trouve avec id: 99");

        verify(partenaireRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Recherche par categorie - Liste de partenaires")
    void getPartenairesByCategorie_ShouldReturnList() {
        // Arrange
        List<Partenaire> professionnels = Arrays.asList(partenaire, autrePartenaire);
        when(partenaireRepository.findByCategorie("PROFESSIONNEL")).thenReturn(professionnels);

        // Act
        List<Partenaire> result = partenaireService.getPartenairesByCategorie("PROFESSIONNEL");

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getCategorie()).isEqualTo("PROFESSIONNEL");
        assertThat(result.get(1).getCategorie()).isEqualTo("PROFESSIONNEL");
        verify(partenaireRepository).findByCategorie("PROFESSIONNEL");
    }

    @Test
    @DisplayName("Recherche par categorie - Liste vide")
    void getPartenairesByCategorie_WhenNoData_ShouldReturnEmptyList() {
        // Arrange
        when(partenaireRepository.findByCategorie("ASSOCIATION")).thenReturn(Arrays.asList());

        // Act
        List<Partenaire> result = partenaireService.getPartenairesByCategorie("ASSOCIATION");

        // Assert
        assertThat(result).isEmpty();
        verify(partenaireRepository).findByCategorie("ASSOCIATION");
    }

    @Test
    @DisplayName("Recherche par statut - Liste de partenaires")
    void getPartenairesByStatut_ShouldReturnList() {
        // Arrange
        List<Partenaire> actifs = Arrays.asList(partenaire, autrePartenaire);
        when(partenaireRepository.findByStatut("ACTIF")).thenReturn(actifs);

        // Act
        List<Partenaire> result = partenaireService.getPartenairesByStatut("ACTIF");

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getStatut()).isEqualTo("ACTIF");
        assertThat(result.get(1).getStatut()).isEqualTo("ACTIF");
        verify(partenaireRepository).findByStatut("ACTIF");
    }

    @Test
    @DisplayName("Recherche par statut - Liste vide")
    void getPartenairesByStatut_WhenNoData_ShouldReturnEmptyList() {
        // Arrange
        when(partenaireRepository.findByStatut("SUSPENDU")).thenReturn(Arrays.asList());

        // Act
        List<Partenaire> result = partenaireService.getPartenairesByStatut("SUSPENDU");

        // Assert
        assertThat(result).isEmpty();
        verify(partenaireRepository).findByStatut("SUSPENDU");
    }

    @Test
    @DisplayName("Recherche par ville - Liste de partenaires")
    void getPartenairesByVille_ShouldReturnList() {
        // Arrange
        List<Partenaire> dakarois = Arrays.asList(partenaire, autrePartenaire);
        when(partenaireRepository.findByVille("Dakar")).thenReturn(dakarois);

        // Act
        List<Partenaire> result = partenaireService.getPartenairesByVille("Dakar");

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getVille()).isEqualTo("Dakar");
        assertThat(result.get(1).getVille()).isEqualTo("Dakar");
        verify(partenaireRepository).findByVille("Dakar");
    }

    @Test
    @DisplayName("Recherche par ville - Liste vide")
    void getPartenairesByVille_WhenNoData_ShouldReturnEmptyList() {
        // Arrange
        when(partenaireRepository.findByVille("Thies")).thenReturn(Arrays.asList());

        // Act
        List<Partenaire> result = partenaireService.getPartenairesByVille("Thies");

        // Assert
        assertThat(result).isEmpty();
        verify(partenaireRepository).findByVille("Thies");
    }

    @Test
    @DisplayName("Compter le nombre total de partenaires")
    void countPartenaires_ShouldReturnTotalCount() {
        // Arrange
        when(partenaireRepository.count()).thenReturn(5L);

        // Act
        long count = partenaireService.countPartenaires();

        // Assert
        assertThat(count).isEqualTo(5L);
        verify(partenaireRepository).count();
    }

    @Test
    @DisplayName("Mise a jour d'un partenaire - Succes")
    void updatePartenaire_WhenExists_ShouldUpdateAndReturnPartenaire() {
        // Arrange
        Partenaire updatedInfo = new Partenaire();
        updatedInfo.setNom("Assurance SUNU Updated");
        updatedInfo.setAdresse("456 Avenue de la Liberte");
        updatedInfo.setTelephone("778887766");
        updatedInfo.setPlafondPriseEnCharge(1500000.0);
        updatedInfo.setEmail("contact@sunu.sn"); // Email inchangé

        when(partenaireRepository.findById(1L)).thenReturn(Optional.of(partenaire));
        when(partenaireRepository.save(any(Partenaire.class))).thenReturn(partenaire);

        // Act
        Partenaire result = partenaireService.updatePartenaire(1L, updatedInfo);

        // Assert
        assertThat(result).isNotNull();
        verify(partenaireRepository).findById(1L);
        verify(partenaireRepository).save(any(Partenaire.class));
    }


    @Test
    @DisplayName("Mise a jour d'un partenaire - Partenaire inexistant (Exception)")
    void updatePartenaire_WhenNotExists_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(partenaireRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> partenaireService.updatePartenaire(99L, partenaire))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("non trouve avec id: 99");

        verify(partenaireRepository).findById(99L);
        verify(partenaireRepository, never()).save(any(Partenaire.class));
    }

}