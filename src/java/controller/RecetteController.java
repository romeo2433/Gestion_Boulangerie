package controller;

import model.Recette;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static util.DatabaseConnection.getConnection;

public class RecetteController {

    // Méthode pour récupérer les recettes par produit
    public List<Recette> getRecetteByProduit(int idProduit) {
        List<Recette> recettes = new ArrayList<>();
        String query = "SELECT id, produit_id, ingredient_id, quantite_utilisee FROM recettes WHERE produit_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idProduit);  // Sélectionner les recettes où le produit correspond à celui passé en paramètre
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Recette recette = new Recette();
                    
                    // Remplir les champs de la recette
                    recette.setId(rs.getInt("id"));
                    recette.setProduitId(rs.getInt("produit_id"));
                    recette.setIngredientId(rs.getInt("ingredient_id"));
                    recette.setQuantiteUtilisee(rs.getDouble("quantite_utilisee"));
                    
                    // Ajouter la recette à la liste
                    recettes.add(recette);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recettes;
    }

    // Méthode pour fabriquer un produit
    public boolean fabricateProduit(int idProduit, int quantiteFabrication) {
        String queryStock = "SELECT ingredient_id, quantite_utilisee FROM recettes WHERE produit_id = ?";
        String updateStock = "UPDATE ingredients SET stock = stock - ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Début de la transaction

            try (PreparedStatement stmtStock = conn.prepareStatement(queryStock)) {
                stmtStock.setInt(1, idProduit); // Sélectionner les ingrédients nécessaires pour le produit
                try (ResultSet rs = stmtStock.executeQuery()) {
                    while (rs.next()) {
                        int idIngredient = rs.getInt("ingredient_id");
                        double quantiteNecessaire = rs.getDouble("quantite_utilisee") * quantiteFabrication;

                        try (PreparedStatement stmtUpdate = conn.prepareStatement(updateStock)) {
                            stmtUpdate.setDouble(1, quantiteNecessaire);
                            stmtUpdate.setInt(2, idIngredient);
                            int affectedRows = stmtUpdate.executeUpdate();

                            if (affectedRows == 0) {
                                conn.rollback(); // Rollback en cas d'échec de l'update
                                return false;
                            }
                        }
                    }
                }
            }
            conn.commit(); // Commit si tout est réussi
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour ajouter un produit à la recette
    public void addProduitRecette(int produitId, double quantite) {
        String query = "INSERT INTO recettes (produit_id, quantite_utilisee) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, produitId);
            stmt.setDouble(2, quantite); // Utilisation de "quantite" en double
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour ajouter un ingrédient à la recette
    public void addIngredientRecette(int ingredientId, double quantite) {
        String query = "INSERT INTO recettes (ingredient_id, quantite_utilisee) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, ingredientId);
            stmt.setDouble(2, quantite); // Utilisation de "quantite" en double
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour récupérer toutes les recettes
    public List<Recette> getAllRecettes() {
        List<Recette> recettes = new ArrayList<>();
        String query = "SELECT * FROM recettes";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Recette recette = new Recette();
                recette.setId(rs.getInt("id"));
                recette.setProduitId(rs.getInt("produit_id"));
                recette.setIngredientId(rs.getInt("ingredient_id"));
                recette.setQuantiteUtilisee(rs.getDouble("quantite_utilisee"));
                recettes.add(recette);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recettes;
    }

    // Méthode pour ajouter une recette avec produit et ingrédient
   public void ajouterRecette(int produitId, int ingredientId, double quantite) {
    System.out.println("Tentative d'ajout d'une recette avec produitId: " + produitId + ", ingredientId: " + ingredientId + ", quantite: " + quantite);
    
    String query = "INSERT INTO recettes (produit_id, ingredient_id, quantite_utilisee) VALUES (?, ?, ?)";
    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        if (produitId > 0) {
            stmt.setInt(1, produitId);
        } else {
            stmt.setNull(1, Types.INTEGER);
        }

        if (ingredientId > 0) {
            stmt.setInt(2, ingredientId);
        } else {
            stmt.setNull(2, Types.INTEGER);
        }

        stmt.setDouble(3, quantite);

        int rowsAffected = stmt.executeUpdate();
        System.out.println("Lignes affectées: " + rowsAffected);
        if (rowsAffected > 0) {
            System.out.println("Recette ajoutée avec succès");
        } else {
            System.out.println("Aucune recette ajoutée.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Erreur lors de l'ajout de la recette.");
    }
}

}
