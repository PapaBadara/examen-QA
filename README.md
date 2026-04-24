
---

# 🏦 Assurance Examen - Backend Spring Boot

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=java"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.2.2-6DB33F?style=for-the-badge&logo=spring-boot"/>
  <img src="https://img.shields.io/badge/Maven-3.9.0-C71A36?style=for-the-badge&logo=apache-maven"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql"/>
  <img src="https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5"/>
  <img src="https://img.shields.io/badge/Mockito-2DAE3A?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/JaCoCo-84%25-success?style=for-the-badge"/>
  <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions"/>
</p>

<p align="center">
🚀 <b>API REST complète pour la gestion des partenaires d'assurance</b><br/>
🎓 Projet d'examen - Qualité Logicielle - Université Amadou Mahtar Mbow (UAM)
</p>

---

# 📋 Table des matières

1. [Description](#-description)
2. [Stack Technique](#-stack-technique)
3. [Métriques de qualité](#-métriques-de-qualité)
4. [Architecture du projet](#-architecture-du-projet)
5. [Collection Postman](#-collection-postman)
6. [API Endpoints](#-api-endpoints)
7. [Quick Start](#-quick-start)
8. [Exécution des tests](#-exécution-des-tests)
9. [Rapports de qualité](#-rapports-de-qualité)
10. [Corrections de qualité apportées](#-corrections-de-qualité-apportées)
11. [Statistiques du projet](#-statistiques-du-projet)
12. [CI/CD Pipeline](#-cicd-pipeline)
13. [Auteur](#-auteur)
14. [Version](#-version)
15. [Liens utiles](#-liens-utiles)
16. [Notes importantes](#-notes-importantes)

---


## 📖 Description

**Assurance Examen** est une API REST développée avec **Spring Boot** pour la gestion centralisée des partenaires d'assurance. Ce projet a été réalisé dans le cadre d'un examen de qualité logicielle, avec pour objectif d'appliquer les bonnes pratiques de développement : validation des données, gestion centralisée des erreurs, tests unitaires et d'intégration, analyse de code avec Checkstyle et JaCoCo, et mise en place d'un pipeline CI/CD.

💡 **L'application permet :**

- Gestion complète des partenaires (CRUD)
- Recherches avancées (par nom, email, catégorie, statut, ville)
- Validation stricte des données entrantes
- Gestion centralisée des exceptions avec codes HTTP appropriés (404, 400, 409)
- Documentation Javadoc complète

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🛠️ Stack Technique

### Backend

| Technologie | Version | Rôle |
|-------------|---------|------|
| ☕ Java | 17 | Langage principal |
| 🍃 Spring Boot | 3.2.2 | Framework principal |
| 📊 Spring Data JPA | 3.2.2 | Accès aux données |
| ✅ Spring Validation | 3.2.2 | Validation des données |
| 🗄️ MySQL | 8.0 | Base de données |
| 🧪 JUnit 5 | 5.10.1 | Tests unitaires |
| 🎭 Mockito | 5.7.0 | Simulation des dépendances |
| 📈 JaCoCo | 0.8.12 | Couverture de tests |
| 🔍 Checkstyle | 9.3 | Convention de code |

### CI/CD

| Service | Utilisation |
|---------|-------------|
| ☁️ GitHub Actions | Pipeline CI/CD |
| 🐳 Docker | Base de données MySQL (tests) |

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📊 Métriques de qualité

| Métrique                        | Valeur      | Statut             |
| ------------------------------- | ----------- | ------------------ |
| **Couverture de code (JaCoCo)** | 84%         | ✅ Seuil atteint    |
| **Couverture des branches**     | 87%         | ✅ Seuil atteint    |
| **Checkstyle violations**       | 19 warnings | ⚠️ En amélioration |
| **Tests unitaires**             | 20 tests    | ✅ 100% passants    |
| **Tests d'intégration**         | 18 tests    | ✅ 100% passants    |
| **Total des tests**             | 38 tests    | ✅ 0 échecs         |

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📁 Architecture du projet

```bash

📦 Assurance_examen
 ┣ 📂 src/main/java/com/riffo/users
 ┃ ┣ 📂 controller
 ┃ ┃ ┗ 📜 PartenaireRESTController.java      # Endpoints REST
 ┃ ┣ 📂 entity
 ┃ ┃ ┗ 📜 Partenaire.java                    # Entité JPA
 ┃ ┣ 📂 exception
 ┃ ┃ ┣ 📜 ResourceNotFoundException.java     # Exception 404
 ┃ ┃ ┣ 📜 DuplicateResourceException.java    # Exception 409
 ┃ ┃ ┣ 📜 ErrorResponse.java                 # Structure erreur
 ┃ ┃ ┗ 📜 GlobalExceptionHandler.java        # Gestionnaire global
 ┃ ┣ 📂 repository
 ┃ ┃ ┗ 📜 PartenaireRepository.java          # Interface JPA
 ┃ ┣ 📂 service
 ┃ ┃ ┣ 📜 PartenaireService.java             # Interface service
 ┃ ┃ ┗ 📂 impl
 ┃ ┃    ┗ 📜 PartenaireServiceImpl.java      # Implémentation
 ┃ ┗ 📜 AssuranceExamenApplication.java      # Classe principale
 ┣ 📂 src/test/java/com/riffo/users
 ┃ ┣ 📂 controller
 ┃ ┃ ┗ 📜 PartenaireRestControllerTest.java  # Tests d'intégration
 ┃ ┗ 📂 service
 ┃    ┗ 📜 PartenaireServiceImplTest.java    # Tests unitaires
 ┣ 📂 .github/workflows
 ┃ ┗ 📜 ci.yml                               # Pipeline CI/CD
 ┣ 📜 Assurance_API_Collection.json           # Collection Postman
 ┣ 📜 checkstyle.xml                          # Règles Checkstyle
 ┣ 📜 pom.xml                                 # Configuration Maven
 ┗ 📜 README.md                               # Documentation
```


**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📦 Collection Postman

Pour faciliter les tests de l'API, une collection Postman complète est fournie dans le projet (`Assurance_API_Collection.json`).

### 📋 Contenu de la collection

| Dossier | Endpoints | Description |
|---------|-----------|-------------|
| **1. Tests GET (Lecture)** | 6 endpoints | Récupération de tous les partenaires et par ID |
| **2. Tests de Recherche** | 7 endpoints | Recherches par nom, email, catégorie, statut, ville |
| **3. Tests CRUD** | 3 endpoints | Création, modification et suppression |
| **4. Tests fonctionnels** | 3 endpoints | Comptage et vérification d'existence |
| **5. Tests d'erreur** | 3 endpoints | 404, 400 et 409 (conflit) |

### 🚀 Comment importer la collection

1. **Ouvrir Postman**
2. Cliquer sur `Import` → `Upload Files`
3. Sélectionner `Assurance_API_Collection.json`
4. La collection apparaît dans l'onglet `Collections`

### ✅ Tests automatiques intégrés

Chaque requête contient des **tests Postman** qui vérifient automatiquement les codes HTTP et les structures de données retournées.

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🔌 API Endpoints

| Méthode | Endpoint | Description | Codes HTTP |
|---------|----------|-------------|------------|
| `GET` | `/api/partenaires` | Liste tous les partenaires | 200 |
| `GET` | `/api/partenaires/{id}` | Récupère un partenaire par ID | 200, 404 |
| `GET` | `/api/partenaires/search/nom` | Recherche par nom | 200, 404 |
| `GET` | `/api/partenaires/search/email` | Recherche par email | 200, 404 |
| `GET` | `/api/partenaires/search/categorie` | Recherche par catégorie | 200 |
| `GET` | `/api/partenaires/search/statut` | Recherche par statut | 200 |
| `GET` | `/api/partenaires/search/ville` | Recherche par ville | 200 |
| `POST` | `/api/partenaires` | Crée un nouveau partenaire | 201, 400, 409 |
| `PUT` | `/api/partenaires/{id}` | Met à jour un partenaire | 200, 400, 404, 409 |
| `DELETE` | `/api/partenaires/{id}` | Supprime un partenaire | 204, 404 |
| `GET` | `/api/partenaires/count` | Compte les partenaires | 200 |
| `GET` | `/api/partenaires/exists/email` | Vérifie existence email | 200 |

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🚀 Quick Start

### 1️⃣ Prérequis

```bash
# Java 17
java -version

# MySQL 8.0
mysql --version

# Maven 3.9+
mvn --version
```

### 2️⃣ Cloner le projet

```bash
git clone https://github.com/PapaBadara/examen-QA.git
cd examen-QA
```

### 3️⃣ Créer la base de données

```bash
mysql -u root -p -e "CREATE DATABASE assurance_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
```

### 4️⃣ Configurer l'application

Modifier `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/assurance_db
spring.datasource.username=root
spring.datasource.password=votre_mot_de_passe
```

### 5️⃣ Lancer l'application

```bash
mvn spring-boot:run
```

### 6️⃣ Tester avec Postman

```bash
# Importer Assurance_API_Collection.json dans Postman
# Exécuter les requêtes dans l'ordre
```

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🧪 Exécution des tests

```bash
# Tous les tests
mvn test

# Tests unitaires uniquement
mvn test -Dtest=PartenaireServiceImplTest

# Tests d'intégration uniquement
mvn test -Dtest=PartenaireRestControllerTest

# Tests avec rapport JaCoCo
mvn clean test jacoco:report

# Ouvrir le rapport de couverture
firefox target/site/jacoco/index.html
```

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📊 Rapports de qualité

| Commande | Rapport |
|----------|---------|
| `mvn checkstyle:checkstyle` | `target/site/checkstyle.html` |
| `mvn jacoco:report` | `target/site/jacoco/index.html` |
| `mvn surefire-report:report` | `target/site/surefire-report.html` |

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🔧 Corrections de qualité apportées

| N° | Défaut | Correction |
|----|--------|------------|
| 1 | Encodage caractères (`cat�gorie`) | Remplacement par `categorie` |
| 2 | Absence validation données | Ajout `@NotBlank`, `@Email`, `@Pattern` |
| 3 | Code verbeux (DRY) | `BeanUtils.copyProperties()` |
| 4 | Mauvaise gestion erreurs | `@RestControllerAdvice` |
| 5 | Dépendances Maven incorrectes | Version 3.2.2 + `spring-boot-starter-web` |
| 6 | Architecture non respectée | Pagination avec `X-Total-Count` |
| 7 | Absence de tests | 38 tests (unitaires + intégration) |
| 8 | Validation métier manquante | Enums `PARTICULIER/PROFESSIONNEL/ASSOCIATION` |

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📈 Statistiques du projet

- 📦 **12 endpoints REST**
- 🧪 **38 tests** (20 unitaires + 18 intégration)
- 🎨 **84% de couverture de code**
- 📁 **8 classes Java principales**
- 🔍 **19 warnings Checkstyle restants**

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🚀 CI/CD Pipeline

Le pipeline GitHub Actions exécute automatiquement :

1. ✅ Compilation du projet
2. ✅ Vérification Checkstyle
3. ✅ Exécution des tests
4. ✅ Génération des rapports JaCoCo
5. ✅ Upload des artefacts (rapports + JAR)
**(https://github.com/PapaBadara/examen-QA/actions)**


**[🔗 Lien vers le pipeline](#-table-des-matières)**

---

## 👨‍💻 Auteur

**Badara DIOP**
<br>
🎓 4e année - Ingénierie des Systèmes d'Information et Données
<br>
🏛️ Université Amadou Mahtar Mbow / Polytech de Diamniadio
<br>
📅 Avril 2026

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📌 Version

```
v1.0 - Avril 2026
```

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 🔗 Liens utiles

- [Documentation Spring Boot](https://spring.io/projects/spring-boot)
- [JaCoCo - Couverture de tests](https://www.jacoco.org/jacoco/)
- [Checkstyle - Convention de code](https://checkstyle.org/)
- [GitHub Actions](https://docs.github.com/en/actions)
- [Postman](https://www.postman.com/)

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

## 📝 Notes importantes

- Le projet utilise **MySQL** en production et **H2** pour les tests
- Les validations sont en français pour une meilleure compréhension
- Les exceptions sont centralisées dans `GlobalExceptionHandler`
- La couverture JaCoCo inclut les tests unitaires et d'intégration

**[🔼 Retour à la table des matières](#-table-des-matières)**

---

<p align="center">
🔥 Clean Code • Tests Automatisés • CI/CD • Production Ready
</p>

---
