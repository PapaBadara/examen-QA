package com.riffo.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riffo.users.entity.Partenaire;
import com.riffo.users.exception.ResourceNotFoundException;
import com.riffo.users.service.PartenaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PartenaireRESTController.class)
@ActiveProfiles("test")
@DisplayName("Tests d'integration des endpoints REST")
class PartenaireRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartenaireService partenaireService;

    private Partenaire partenaire1;
    private Partenaire partenaire2;

    @BeforeEach
    void setUp() {
        // Initialisation du partenaire 1
        partenaire1 = new Partenaire();
        partenaire1.setId(1L);
        partenaire1.setNom("Assurance SUNU");
        partenaire1.setCategorie("PROFESSIONNEL");
        partenaire1.setAdresse("123 Rue de la Paix");
        partenaire1.setVille("Dakar");
        partenaire1.setTelephone("771234567");
        partenaire1.setEmail("contact@sunu.sn");
        partenaire1.setLatitude(14.7167);
        partenaire1.setLongitude(-17.4677);
        partenaire1.setStatut("ACTIF");
        partenaire1.setPlafondPriseEnCharge(1000000.0);

        // Initialisation du partenaire 2
        partenaire2 = new Partenaire();
        partenaire2.setId(2L);
        partenaire2.setNom("AXA Assurance");
        partenaire2.setCategorie("PROFESSIONNEL");
        partenaire2.setAdresse("45 Avenue Cheikh Anta Diop");
        partenaire2.setVille("Dakar");
        partenaire2.setTelephone("778887766");
        partenaire2.setEmail("contact@axa.sn");
        partenaire2.setLatitude(14.7167);
        partenaire2.setLongitude(-17.4677);
        partenaire2.setStatut("ACTIF");
        partenaire2.setPlafondPriseEnCharge(2000000.0);
    }

    // ==================== TEST GET ====================

    @Test
    @DisplayName("GET /partenaires - Recuperer tous les partenaires")
    void getAllPartenaires_ShouldReturnListOfPartenaires() throws Exception {
        // Arrange
        List<Partenaire> partenaires = Arrays.asList(partenaire1, partenaire2);
        when(partenaireService.getAllPartenaires()).thenReturn(partenaires);

        // Act & Assert
        mockMvc.perform(get("/partenaires")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nom").value("Assurance SUNU"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nom").value("AXA Assurance"));

        verify(partenaireService).getAllPartenaires();
    }

    @Test
    @DisplayName("GET /partenaires - Liste vide")
    void getAllPartenaires_WhenNoData_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(partenaireService.getAllPartenaires()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/partenaires")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

        verify(partenaireService).getAllPartenaires();
    }

    // ==================== TEST GET BY ID ====================

    @Test
    @DisplayName("GET /partenaires/{id} - Partenaire existe")
    void getPartenaireById_WhenExists_ShouldReturnPartenaire() throws Exception {
        // Arrange
        when(partenaireService.getPartenaireById(1L)).thenReturn(partenaire1);

        // Act & Assert
        mockMvc.perform(get("/partenaires/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Assurance SUNU"))
                .andExpect(jsonPath("$.email").value("contact@sunu.sn"));

        verify(partenaireService).getPartenaireById(1L);
    }

    @Test
    @DisplayName("GET /partenaires/{id} - Partenaire inexistant (404)")
    void getPartenaireById_WhenNotExists_ShouldReturn404() throws Exception {
        // Arrange
        when(partenaireService.getPartenaireById(99L)).thenThrow(new ResourceNotFoundException("Partenaire non trouve avec id: 99"));

        // Act & Assert
        mockMvc.perform(get("/partenaires/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Partenaire non trouve avec id: 99"));

        verify(partenaireService).getPartenaireById(99L);
    }

    // ==================== TEST POST ====================

    @Test
    @DisplayName("POST /partenaires - Creation d'un partenaire avec donnees valides")
    void addPartenaire_WithValidData_ShouldReturnCreated() throws Exception {
        // Arrange
        when(partenaireService.addPartenaire(any(Partenaire.class))).thenReturn(partenaire1);

        // Act & Assert
        mockMvc.perform(post("/partenaires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partenaire1)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Assurance SUNU"))
                .andExpect(jsonPath("$.email").value("contact@sunu.sn"));

        verify(partenaireService).addPartenaire(any(Partenaire.class));
    }

    @Test
    @DisplayName("POST /partenaires - Creation avec donnees invalides (400)")
    void addPartenaire_WithInvalidData_ShouldReturn400() throws Exception {
        // Arrange - Partenaire avec email invalide
        Partenaire invalidPartenaire = new Partenaire();
        invalidPartenaire.setNom("Test");
        invalidPartenaire.setEmail("email-invalide");

        // Act & Assert
        mockMvc.perform(post("/partenaires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidPartenaire)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(partenaireService, never()).addPartenaire(any(Partenaire.class));
    }

    // ==================== TEST PUT ====================

    @Test
    @DisplayName("PUT /partenaires/{id} - Mise a jour d'un partenaire")
    void updatePartenaire_WhenExists_ShouldReturnUpdatedPartenaire() throws Exception {
        // Arrange
        Partenaire updatedPartenaire = new Partenaire();
        updatedPartenaire.setNom("Assurance SUNU Updated");
        updatedPartenaire.setCategorie("PROFESSIONNEL");
        updatedPartenaire.setAdresse("456 Avenue de la Liberte");
        updatedPartenaire.setVille("Dakar");
        updatedPartenaire.setTelephone("778887766");
        updatedPartenaire.setEmail("contact@sunu.sn");
        updatedPartenaire.setLatitude(14.7167);
        updatedPartenaire.setLongitude(-17.4677);
        updatedPartenaire.setStatut("ACTIF");
        updatedPartenaire.setPlafondPriseEnCharge(1500000.0);
        updatedPartenaire.setId(1L);

        when(partenaireService.updatePartenaire(eq(1L), any(Partenaire.class))).thenReturn(updatedPartenaire);

        // Act & Assert
        mockMvc.perform(put("/partenaires/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPartenaire)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Assurance SUNU Updated"));

        verify(partenaireService).updatePartenaire(eq(1L), any(Partenaire.class));
    }

    // ==================== TEST DELETE ====================

    @Test
    @DisplayName("DELETE /partenaires/{id} - Suppression d'un partenaire - Succes")
    void deletePartenaire_WhenExists_ShouldReturn204() throws Exception {
        // Arrange
        doNothing().when(partenaireService).deletePartenaire(1L);

        // Act & Assert
        mockMvc.perform(delete("/partenaires/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(partenaireService).deletePartenaire(1L);
    }

    @Test
    @DisplayName("DELETE /partenaires/{id} - Suppression partenaire inexistant (404)")
    void deletePartenaire_WhenNotExists_ShouldReturn404() throws Exception {
        // Arrange
        doThrow(new ResourceNotFoundException("Partenaire non trouve avec id: 99"))
                .when(partenaireService).deletePartenaire(99L);

        // Act & Assert
        mockMvc.perform(delete("/partenaires/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(partenaireService).deletePartenaire(99L);
    }

    // ==================== TESTS DES RECHERCHES ====================

    @Test
    @DisplayName("GET /partenaires/search/nom - Recherche par nom")
    void getPartenaireByNom_WhenExists_ShouldReturnPartenaire() throws Exception {
        // Arrange
        when(partenaireService.getPartenaireByNom("Assurance SUNU")).thenReturn(partenaire1);

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/nom")
                        .param("nom", "Assurance SUNU")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Assurance SUNU"));
    }

    @Test
    @DisplayName("GET /partenaires/search/email - Recherche par email")
    void getPartenaireByEmail_WhenExists_ShouldReturnPartenaire() throws Exception {
        // Arrange
        when(partenaireService.getPartenaireByEmail("contact@sunu.sn")).thenReturn(partenaire1);

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/email")
                        .param("email", "contact@sunu.sn")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("contact@sunu.sn"));
    }

    @Test
    @DisplayName("GET /partenaires/search/categorie - Recherche par categorie")
    void getPartenairesByCategorie_ShouldReturnList() throws Exception {
        // Arrange
        List<Partenaire> professionnels = Arrays.asList(partenaire1, partenaire2);
        when(partenaireService.getPartenairesByCategorie("PROFESSIONNEL")).thenReturn(professionnels);

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/categorie")
                        .param("categorie", "PROFESSIONNEL")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    // ==================== TESTS DES ENDPOINTS UTILITAIRES ====================

    @Test
    @DisplayName("GET /partenaires/count - Compter les partenaires")
    void countPartenaires_ShouldReturnCount() throws Exception {
        // Arrange
        when(partenaireService.countPartenaires()).thenReturn(5L);

        // Act & Assert
        mockMvc.perform(get("/partenaires/count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    @DisplayName("GET /partenaires/exists/email - Verifier existence email")
    void existsByEmail_ShouldReturnBoolean() throws Exception {
        // Arrange
        when(partenaireService.existsByEmail("contact@sunu.sn")).thenReturn(true);

        // Act & Assert
        mockMvc.perform(get("/partenaires/exists/email")
                        .param("email", "contact@sunu.sn")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("GET /partenaires/search/statut - Recherche par statut")
    void getPartenairesByStatut_ShouldReturnList() throws Exception {
        // Arrange
        List<Partenaire> actifs = Arrays.asList(partenaire1, partenaire2);
        when(partenaireService.getPartenairesByStatut("ACTIF")).thenReturn(actifs);

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/statut")
                        .param("statut", "ACTIF")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].statut").value("ACTIF"))
                .andExpect(jsonPath("$[1].statut").value("ACTIF"));

        verify(partenaireService).getPartenairesByStatut("ACTIF");
    }

    @Test
    @DisplayName("GET /partenaires/search/statut - Liste vide")
    void getPartenairesByStatut_WhenNoData_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(partenaireService.getPartenairesByStatut("INACTIF")).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/statut")
                        .param("statut", "INACTIF")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /partenaires/search/ville - Recherche par ville")
    void getPartenairesByVille_ShouldReturnList() throws Exception {
        // Arrange
        List<Partenaire> dakarois = Arrays.asList(partenaire1, partenaire2);
        when(partenaireService.getPartenairesByVille("Dakar")).thenReturn(dakarois);

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/ville")
                        .param("ville", "Dakar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].ville").value("Dakar"))
                .andExpect(jsonPath("$[1].ville").value("Dakar"));

        verify(partenaireService).getPartenairesByVille("Dakar");
    }

    @Test
    @DisplayName("GET /partenaires/search/ville - Liste vide")
    void getPartenairesByVille_WhenNoData_ShouldReturnEmptyList() throws Exception {
        // Arrange
        when(partenaireService.getPartenairesByVille("Thies")).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/partenaires/search/ville")
                        .param("ville", "Thies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}