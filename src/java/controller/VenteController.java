package controller;

import model.Vente;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Vendeur;

public class VenteController {

   public List<Vente> getAllVentes() {
    List<Vente> ventes = new ArrayList<>();
    String query = "SELECT v.id AS idVente, p.nom AS nomProduit, ven.nom AS nomVendeur, s.libelle AS sexeVendeur, " +
                   "v.date_vente, v.quantite, (v.quantite * p.prix) AS montantTotal, " +
                   "((v.quantite * p.prix) * 0.05) AS commissionVendeur " +
                   "FROM ventes v " +
                   "JOIN produits p ON v.produit_id = p.id " +
                   "JOIN vendeurs ven ON v.vendeur_id = ven.id " +
                   "JOIN sexe s ON ven.sexe_id = s.id"; // Ajout de la jointure avec la table sexe

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Vente vente = new Vente(
                    rs.getInt("idVente"),
                    rs.getString("nomProduit"),
                    rs.getTimestamp("date_vente").toLocalDateTime(),
                    rs.getInt("quantite"),
                    rs.getDouble("montantTotal"),
                    rs.getString("nomVendeur"),
                    rs.getString("sexeVendeur"), // Récupération du sexe
                    rs.getDouble("commissionVendeur")
            );
            ventes.add(vente);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ventes;
}
   
  public String effectuerVente(int produitId, int quantite, int vendeurId, LocalDateTime dateVente) {
    String message = "";

    // Validation des entrées
    if (quantite <= 0) {
        return "La quantité doit être supérieure à zéro.";
    }

    String checkStockQuery = "SELECT quantite FROM stock_produits WHERE produit_id = ?";
    String insertVenteQuery = "INSERT INTO ventes (produit_id, quantite, vendeur_id, date_vente) VALUES (?, ?, ?, ?)";
    String updateStockQuery = "UPDATE stock_produits SET quantite = quantite - ? WHERE produit_id = ?";

    Connection conn = null;
    PreparedStatement checkStockStmt = null;
    PreparedStatement insertVenteStmt = null;
    PreparedStatement updateStockStmt = null;
    ResultSet rs = null;

    try {
        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false); // Désactiver l'autocommit pour gérer manuellement la transaction

        // Vérifier le stock disponible
        checkStockStmt = conn.prepareStatement(checkStockQuery);
        checkStockStmt.setInt(1, produitId);
        rs = checkStockStmt.executeQuery();

        if (rs.next()) {
            int stockDisponible = rs.getInt("quantite");

            if (quantite > stockDisponible) {
                return "Quantité insuffisante en stock. Stock disponible : " + stockDisponible;
            }

            // Insérer la vente avec la date spécifiée
            insertVenteStmt = conn.prepareStatement(insertVenteQuery);
            insertVenteStmt.setInt(1, produitId);
            insertVenteStmt.setInt(2, quantite);
            insertVenteStmt.setInt(3, vendeurId);
            insertVenteStmt.setTimestamp(4, java.sql.Timestamp.valueOf(dateVente)); // Convertir LocalDateTime en Timestamp
            insertVenteStmt.executeUpdate();

            // Mettre à jour le stock
            updateStockStmt = conn.prepareStatement(updateStockQuery);
            updateStockStmt.setInt(1, quantite);
            updateStockStmt.setInt(2, produitId);
            updateStockStmt.executeUpdate();

            // Commit de la transaction
            conn.commit();
            message = "Vente réussie ! Quantité vendue : " + quantite;
        } else {
            message = "Produit introuvable.";
        }
    } catch (SQLException e) {
        // Rollback en cas d'erreur
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
        message = "Erreur lors de l'enregistrement de la vente : " + e.getMessage();
    } finally {
        // Fermer les ressources
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (checkStockStmt != null) {
            try {
                checkStockStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (insertVenteStmt != null) {
            try {
                insertVenteStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (updateStockStmt != null) {
            try {
                updateStockStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Réactiver l'autocommit
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return message;
}
  
  public List<Vente> getVentesFiltrees(String sexe, LocalDate dateDebut, LocalDate dateFin) {
    List<Vente> ventes = new ArrayList<>();
    String query = "SELECT v.id AS idVente, p.nom AS nomProduit, ven.nom AS nomVendeur, s.libelle AS sexeVendeur, " +
                   "v.date_vente, v.quantite, (v.quantite * p.prix) AS montantTotal, " +
                   "((v.quantite * p.prix) * 0.05) AS commissionVendeur " +
                   "FROM ventes v " +
                   "JOIN produits p ON v.produit_id = p.id " +
                   "JOIN vendeurs ven ON v.vendeur_id = ven.id " +
                   "JOIN sexe s ON ven.sexe_id = s.id " +
                   "WHERE 1=1"; // Condition toujours vraie pour faciliter l'ajout de filtres

    if (sexe != null && !sexe.isEmpty()) {
        query += " AND s.libelle = ?";
    }
    if (dateDebut != null) {
        query += " AND v.date_vente >= ?";
    }
    if (dateFin != null) {
        query += " AND v.date_vente <= ?";
    }

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        int paramIndex = 1;
        if (sexe != null && !sexe.isEmpty()) {
            stmt.setString(paramIndex++, sexe);
        }
        if (dateDebut != null) {
            stmt.setDate(paramIndex++, java.sql.Date.valueOf(dateDebut));
        }
        if (dateFin != null) {
            stmt.setDate(paramIndex++, java.sql.Date.valueOf(dateFin));
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Vente vente = new Vente(
                    rs.getInt("idVente"),
                    rs.getString("nomProduit"),
                    rs.getTimestamp("date_vente").toLocalDateTime(),
                    rs.getInt("quantite"),
                    rs.getDouble("montantTotal"),
                    rs.getString("nomVendeur"),
                    rs.getString("sexeVendeur"),
                    rs.getDouble("commissionVendeur")
            );
            ventes.add(vente);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ventes;
}
  public List<Vendeur> getAllVendeurs() {
    List<Vendeur> vendeurs = new ArrayList<>();
    String query = "SELECT ven.id, ven.nom, s.libelle AS sexe " + // Ajout du sexe
                   "FROM vendeurs ven " +
                   "JOIN sexe s ON ven.sexe_id = s.id"; // Jointure avec la table sexe

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Vendeur vendeur = new Vendeur(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("sexe") // Récupération du sexe
            );
            vendeurs.add(vendeur);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return vendeurs;
}
public Map<String, Double> getMontantTotalParSexe() {
    Map<String, Double> montantParSexe = new HashMap<>();
    String query = "SELECT s.libelle AS sexe, SUM(v.quantite * p.prix) AS montant_total " +
                   "FROM ventes v " +
                   "JOIN produits p ON v.produit_id = p.id " +
                   "JOIN vendeurs ven ON v.vendeur_id = ven.id " +
                   "JOIN sexe s ON ven.sexe_id = s.id " +
                   "GROUP BY s.libelle";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String sexe = rs.getString("sexe");
            double montantTotal = rs.getDouble("montant_total");
            montantParSexe.put(sexe, montantTotal);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return montantParSexe;
}
}
