/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;




import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produit;
import model.Recommandation;
import util.DatabaseConnection;

public class RecommandationController {

    // Récupérer toutes les recommandations
    public List<Recommandation> getAllRecommandations() {
        List<Recommandation> recommandations = new ArrayList<>();
        String query = "SELECT r.id_recomandation, r.date_debut, r.date_fin, p.id, p.nom "
                     + "FROM recommandation r "
                     + "JOIN produits p ON r.idProduit = p.id        ";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produit produit = new Produit(rs.getInt("id"), rs.getString("nom"));
                Recommandation recommandation = new Recommandation(
                        rs.getInt("id_recomandation"),
                        rs.getDate("date_debut"),
                        rs.getDate("date_fin"),
                        produit
                );
                recommandations.add(recommandation);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des recommandations : " + e.getMessage());
        }
        return recommandations;
    }

    // Ajouter une recommandation
    public boolean addRecommandation( int idProduit, String dateDebut , String dateFin) {
        String query = "INSERT INTO recommandation ( date_debut, date_fin, idProduit) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
          stmt.setDate(1, java.sql.Date.valueOf(dateDebut));
            stmt.setDate(2, java.sql.Date.valueOf(dateFin));
            stmt.setInt(3, idProduit);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la recommandation : " + e.getMessage());
            return false;
        }
    }

    // Modifier une recommandation
    public boolean editRecommandation(String idRecommandation, Date dateDebut, Date dateFin, String idProduit) {
        String query = "UPDATE recommandation SET date_debut = ?, date_fin = ?, idProduit = ? WHERE id_recomandation = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, dateDebut);
            stmt.setDate(2, dateFin);
            stmt.setString(3, idProduit);
            stmt.setString(4, idRecommandation);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la recommandation : " + e.getMessage());
            return false;
        }
    }

    // Supprimer une recommandation
    public boolean deleteRecommandation(String idRecommandation) {
        String query = "DELETE FROM recommandation WHERE id_recomandation = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idRecommandation);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la recommandation : " + e.getMessage());
            return false;
        }
    }
}
