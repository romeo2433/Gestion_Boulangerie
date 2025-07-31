package controller;

import model.DetailCommande;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetailCommandeController {

    public List<DetailCommande> getAllDetailCommandes() {
        List<DetailCommande> detailCommandes = new ArrayList<>();
        String query = "SELECT " +
                "c.id AS idCommande, " +
                "c.date_commande, " +
                "c.statut, " +
                "cl.id AS idClient, " +
                "cl.nom AS nomClient, " +
                "p.id AS idProduit, " +
                "p.nom AS produit, " +
                "dc.quantite " +
                "FROM commandes c " +
                "JOIN clients cl ON c.idclient = cl.id " +
                "JOIN details_commandes dc ON c.id = dc.commande_id " +
                "JOIN produits p ON dc.produit_id = p.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                DetailCommande detailCommande = new DetailCommande(
                        rs.getInt("idCommande"),
                        rs.getString("date_commande"),
                        rs.getString("statut"),
                        rs.getInt("idClient"),
                        rs.getString("nomClient"),
                        rs.getInt("idProduit"),
                        rs.getString("produit"),
                        rs.getInt("quantite")
                );

                detailCommandes.add(detailCommande);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailCommandes;
    }
    
    
}
