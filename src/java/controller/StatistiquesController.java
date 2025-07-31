package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Statistique;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class StatistiquesController {
    private final List<Statistique> statistiques;

    public StatistiquesController() {
        this.statistiques = new ArrayList<>();
    }

    public List<Statistique> getAllStatistiques() {
        return statistiques;
    }

    public void addStatistique(int id, String date, double benefices, double pertes, int produitPopulaire) {
        Statistique statistique = new Statistique(id, date, benefices, pertes, produitPopulaire);
        statistiques.add(statistique);
    }

    public void updateStatistique(int id, double benefices, double pertes) {
        for (Statistique statistique : statistiques) {
            if (statistique.getId() == id) {
                statistique.setBenefices(benefices);
                statistique.setPertes(pertes);
                break;
            }
        }
    }
    
    public List<String> verifierStockFaible() {
    List<String> alertes = new ArrayList<>();
    String queryProduits = "SELECT p.nom FROM stocks_produits sp JOIN produits p ON sp.produit_id = p.id WHERE sp.quantite_disponible < sp.seuil_minimum";
    String queryIngredients = "SELECT i.nom FROM stocks_ingredients si JOIN ingredients i ON si.ingredient_id = i.id WHERE si.quantite_disponible < si.seuil_minimum";
    
    try (Connection conn = DatabaseConnection.getConnection();
         Statement stmt = conn.createStatement()) {
        ResultSet rsProduits = stmt.executeQuery(queryProduits);
        while (rsProduits.next()) {
            alertes.add("Stock faible pour le produit : " + rsProduits.getString("nom"));
        }
        
        ResultSet rsIngredients = stmt.executeQuery(queryIngredients);
        while (rsIngredients.next()) {
            alertes.add("Stock faible pour l'ingrédient : " + rsIngredients.getString("nom"));
        }
    } catch (SQLException e) {
    }
    return alertes;
}

}
