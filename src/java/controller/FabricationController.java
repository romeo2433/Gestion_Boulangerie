/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Romeo
 */
public class FabricationController {
   public boolean verifierStocks(Connection connection, int produitId, int quantiteFabriquee) throws SQLException {
    String sql = "SELECT si.ingredient_id, si.quantite AS stock_disponible, "
               + "       r.quantite_utilisee * ? AS quantite_requise "
               + "FROM recettes r "
               + "JOIN stock_ingredients si ON r.ingredient_id = si.ingredient_id "
               + "WHERE r.produit_id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, quantiteFabriquee); // Quantité à fabriquer
        stmt.setInt(2, produitId);        // ID du produit

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int stockDisponible = rs.getInt("stock_disponible");
                int quantiteRequise = rs.getInt("quantite_requise");

                if (stockDisponible < quantiteRequise) {
                    return false;  // Stock insuffisant pour un ingrédient
                }
            }
        }
    }
    return true;  // Tous les stocks sont suffisants
}
   
   
   
   
   public void mettreAJourStocks(Connection connection, int produitId, int quantiteFabriquee) throws SQLException {
    String sql = "UPDATE stock_ingredients "
               + "SET quantite = quantite - ( "
               + "    SELECT r.quantite_utilisee * ? "
               + "    FROM recettes r "
               + "    WHERE r.ingredient_id = stock_ingredients.ingredient_id "
               + "    AND r.produit_id = ? "
               + ") "
               + "WHERE ingredient_id IN ( "
               + "    SELECT ingredient_id "
               + "    FROM recettes "
               + "    WHERE produit_id = ? "
               + ")";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, quantiteFabriquee);
        stmt.setInt(2, produitId);
        stmt.setInt(3, produitId);
        stmt.executeUpdate();
    }
}

   
   public void insererStockProduit(Connection connection, int produitId, int quantiteFabriquee) throws SQLException {
    String sql = "INSERT INTO stock_produits (produit_id, quantite) " +
             "VALUES (?, ?) " +
             "ON CONFLICT (produit_id) " +
             "DO UPDATE SET quantite = stock_produits.quantite + EXCLUDED.quantite";


try (PreparedStatement statement = connection.prepareStatement(sql)) {
    statement.setInt(1, produitId);  // produit_id
    statement.setInt(2, quantiteFabriquee);  // quantité à ajouter
    statement.executeUpdate();
}

}
   
     /**
     * Vérifie si une recette existe pour un produit donné.
     * 
     * @param connection La connexion à la base de données.
     * @param produitId L'ID du produit à vérifier.
     * @return true si une recette existe pour le produit, sinon false.
     * @throws SQLException En cas d'erreur SQL.
     */
    public boolean verifierRecette(Connection connection, int produitId) throws SQLException {
        String sql = "SELECT 1 FROM recettes WHERE produit_id = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, produitId); // Remplacer le ? par le produitId
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Retourne true si au moins une ligne existe
            }
        }
    }


}
