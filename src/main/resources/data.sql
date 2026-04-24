-- Insertion de 5 partenaires d'assurance
INSERT INTO partenaires (nom, categorie, adresse, ville, telephone, email, latitude, longitude, statut, plafond_prise_en_charge) VALUES
('Assurance SUNU', 'PROFESSIONNEL', '123 Rue de la Paix', 'Dakar', '771234567', 'contact@sunu.sn', 14.7167, -17.4677, 'ACTIF', 1000000),
('AXA Assurance', 'PROFESSIONNEL', '45 Avenue Cheikh Anta Diop', 'Dakar', '778887766', 'contact@axa.sn', 14.7167, -17.4677, 'ACTIF', 2000000),
('NSIA Assurance', 'PROFESSIONNEL', '89 Boulevard de la Republique', 'Dakar', '770001122', 'contact@nsia.sn', 14.7167, -17.4677, 'ACTIF', 1500000),
('Jean Dupont', 'PARTICULIER', '12 Rue des Fleurs', 'Thies', '776655443', 'jean.dupont@gmail.com', 14.7833, -16.9167, 'ACTIF', 500000),
('Marie Diop', 'PARTICULIER', '34 Cite des Enseignants', 'Touba', '779988776', 'marie.diop@yahoo.fr', 14.8667, -15.8833, 'INACTIF', 300000);
