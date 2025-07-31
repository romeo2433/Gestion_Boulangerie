package controller;

import model.StockProduit;
import model.StockIngredient;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockController {

    private final Connection connection;

    // Constructeur sans paramètres
    public StockController() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
public List<StockProduit> getAllStockProduits() {
    List<StockProduit> stockProduits = new ArrayList<>();
    String query = "SELECT sp.id AS stock_id, sp.produit_id, sp.quantite, p.nom, p.seuil_minimum " +
                   "FROM stock_produits sp " +
                   "JOIN produits p ON sp.produit_id = p.id";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("stock_id");
            int produitId = rs.getInt("produit_id");
            int quantite = rs.getInt("quantite");
            String nom = rs.getString("nom");
            int seuilMinimum = rs.getInt("seuil_minimum");

            StockProduit stockProduit = new StockProduit(id, produitId, quantite, nom, seuilMinimum);
            stockProduits.add(stockProduit);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return stockProduits;
}




    // Méthode pour récupérer les stocks des ingrédients
  public List<StockIngredient> getStocksIngredients() {
    List<StockIngredient> stockIngredients = new ArrayList<>();
    String query = "SELECT i.nom, si.quantite " +
                   "FROM stock_ingredients si " +
                   "JOIN ingredients i ON si.ingredient_id = i.id";

    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            StockIngredient ingredient = new StockIngredient(
                    rs.getString("nom"),  // Nom de l'ingrédient depuis la table ingredients
                    rs.getString("quantite"),  // Quantité disponible dans stock_ingredients
                    null  // Seuil minimum (ou obtenir une autre colonne si nécessaire)
            );
            stockIngredients.add(ingredient);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return stockIngredients;
}


    // Méthode pour vérifier les stocks faibles
    

    

    // Méthode pour fermer la connexion
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
