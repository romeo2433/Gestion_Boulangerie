-- Création de la base de données
CREATE DATABASE boulangerie;
\c boulangerie;

-- Table pour les catégories de produits
CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE
);

-- Table pour les unités de mesure des ingrédients
CREATE TABLE unite (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL UNIQUE
);

-- Table pour les ingrédients
CREATE TABLE ingredients (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE,
    unite_id INTEGER REFERENCES unite(id) ON DELETE SET NULL,
    prix_unitaire NUMERIC(10, 2) NOT NULL CHECK (prix_unitaire >= 0),
    seuil_minimum INTEGER DEFAULT 0 CHECK (seuil_minimum >= 0)
);

-- Table pour les produits
CREATE TABLE produits (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    prix NUMERIC(10, 2) NOT NULL CHECK (prix >= 0),
    categorie_id INTEGER REFERENCES categories(id) ON DELETE SET NULL,
    seuil_minimum INTEGER DEFAULT 0 CHECK (seuil_minimum >= 0)
);

-- Table pour les recettes (association entre produits et ingrédients)
CREATE TABLE recettes (
    id SERIAL PRIMARY KEY,
    produit_id INTEGER REFERENCES produits(id) ON DELETE CASCADE,
    ingredient_id INTEGER REFERENCES ingredients(id) ON DELETE CASCADE,
    quantite_utilisee NUMERIC(10, 2) NOT NULL CHECK (quantite_utilisee > 0)
);

-- Table pour le stock des ingrédients
CREATE TABLE stock_ingredients (
    id SERIAL PRIMARY KEY,
    ingredient_id INTEGER REFERENCES ingredients(id) ON DELETE CASCADE,
    quantite INTEGER NOT NULL CHECK (quantite >= 0)
);

-- Table pour le stock des produits
CREATE TABLE stock_produits (
    id SERIAL PRIMARY KEY,
    produit_id INTEGER REFERENCES produits(id) ON DELETE CASCADE,
    quantite INTEGER NOT NULL CHECK (quantite >= 0)
);

-- Table pour les utilisateurs (clients, vendeurs, administrateurs)
CREATE TABLE utilisateurs (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    mot_de_passe VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL CHECK (role IN ('admin', 'client', 'vendeur'))
);

-- Table pour les sexes (Homme, Femme, Autre)
CREATE TABLE sexe (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL UNIQUE
);

-- Table pour les vendeurs
CREATE TABLE vendeurs (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    sexe_id INTEGER REFERENCES sexe(id) ON DELETE SET NULL
);

-- Table pour les clients
CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    telephone VARCHAR(20),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table pour les commandes
CREATE TABLE commandes (
    id SERIAL PRIMARY KEY,
    client_id INTEGER REFERENCES clients(id) ON DELETE CASCADE,
    date_commande TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statut VARCHAR(50) NOT NULL CHECK (statut IN ('en attente', 'confirmée', 'expédiée', 'livrée', 'annulée'))
);

-- Table pour les détails des commandes (produits commandés)
CREATE TABLE details_commandes (
    id SERIAL PRIMARY KEY,
    commande_id INTEGER REFERENCES commandes(id) ON DELETE CASCADE,
    produit_id INTEGER REFERENCES produits(id) ON DELETE CASCADE,
    quantite INTEGER NOT NULL CHECK (quantite > 0),
    prix_unitaire NUMERIC(10, 2) NOT NULL CHECK (prix_unitaire >= 0)
);

-- Table pour les ventes
CREATE TABLE ventes (
    id SERIAL PRIMARY KEY,
    produit_id INTEGER REFERENCES produits(id) ON DELETE CASCADE,
    vendeur_id INTEGER REFERENCES vendeurs(id) ON DELETE SET NULL,
    date_vente TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    quantite INTEGER NOT NULL CHECK (quantite > 0)
);

-- Table pour les recommandations de produits
CREATE TABLE recommandation (
    id SERIAL PRIMARY KEY,
    date_debut DATE,
    date_fin DATE NOT NULL,
    produit_id INTEGER REFERENCES produits(id) ON DELETE CASCADE
);

CREATE TABLE commande_produits (
    id SERIAL PRIMARY KEY,
    commande_id INTEGER,
    produit_id INTEGER,
    quantite INTEGER NOT NULL,
    CONSTRAINT commande_produits_quantite_check CHECK (quantite > 0),
    CONSTRAINT commande_produits_commande_id_fkey FOREIGN KEY (commande_id) REFERENCES commandes(id) ON DELETE CASCADE,
    CONSTRAINT commande_produits_produit_id_fkey FOREIGN KEY (produit_id) REFERENCES produits(id) ON DELETE CASCADE
);






INSERT INTO unite (nom) VALUES
('Kilogramme'),
('Litre'),
('Pièce');



INSERT INTO categories (nom) VALUES
('Pain'),
('Viennoiserie'),
('Pâtisserie'),
('Boissons');




INSERT INTO ingredients (nom, unite_id, prix_unitaire, seuil_minimum) VALUES
('Farine', 1, 0.5, 10),
('Beurre', 1, 2.0, 5),
('Sucre', 1, 1.0, 8),
('Levure', 3, 0.2, 20),
('Chocolat', 1, 3.0, 5),
('Lait', 2, 1.5, 15),
('Œufs', 3, 0.3, 50);



INSERT INTO produits (nom, description, prix, categorie_id, seuil_minimum) VALUES
('Baguette', 'Pain classique français', 1.00, 1, 20),
('Croissant', 'Viennoiserie légère et beurrée', 1.20, 2, 30),
('Tarte aux Pommes', 'Pâte sablée garnie de pommes', 3.50, 3, 10),
('Café Expresso', 'Café noir fort', 2.00, 4, 50);




INSERT INTO recettes (produit_id, ingredient_id, quantite_utilisee) VALUES
(1, 1, 0.5),  -- Baguette : 0.5 kg de farine
(1, 4, 0.02), -- Baguette : 20 g de levure
(2, 1, 0.3),  -- Croissant : 0.3 kg de farine
(2, 2, 0.2);  -- Croissant : 0.2 kg de beurre



INSERT INTO utilisateurs (nom, email, mot_de_passe, role) VALUES
('Admin', 'admin@boulangerie.com', '1234', 'admin'),
('Client1', 'client1@boulangerie.com', 'client123', 'client'),
('Vendeur1', 'vendeur1@boulangerie.com', 'vendeur123', 'vendeur');




INSERT INTO sexe (libelle) VALUES
('Homme'),
('Femme'),
('Autre');



INSERT INTO vendeurs (nom, sexe_id) VALUES
('Jean Dupont', 1),
('Marie Curie', 2);


INSERT INTO clients (nom, email, telephone) VALUES
('Paul Durand', 'paul.durand@example.com', '+33 6 12 34 56 78'),
('Claire Martin', 'claire.martin@example.com', '+33 6 98 76 54 32');



INSERT INTO commandes (client_id, statut) VALUES
(1, 'en attente'),
(2, 'confirmée');




INSERT INTO details_commandes (commande_id, produit_id, quantite, prix_unitaire) VALUES
(1, 1, 3, 1.00),  -- Commande 1 : 3 Baguettes
(1, 2, 2, 1.20),  -- Commande 1 : 2 Croissants
(2, 3, 1, 3.50);  -- Commande 2 : 1 Tarte aux Pommes



SELECT 
    s.libelle AS sexe, 
    SUM(v.quantite * p.prix) AS montant_total
FROM 
    ventes v
JOIN 
    produits p ON v.produit_id = p.id
JOIN 
    vendeurs ven ON v.vendeur_id = ven.id
JOIN 
    sexe s ON ven.sexe_id = s.id
GROUP BY 
    s.libelle;





SELECT 
    c.id AS commande_id,
    cl.nom AS client,
    p.nom AS produit,
    dc.quantite,
    dc.prix_unitaire,
    (dc.quantite * dc.prix_unitaire) AS montant_total
FROM 
    details_commandes dc
JOIN 
    commandes c ON dc.commande_id = c.id
JOIN 
    clients cl ON c.client_id = cl.id
JOIN 
    produits p ON dc.produit_id = p.id;

