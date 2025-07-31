package controller;

import model.Commande;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeController {

   public List<Commande> getAllCommandes() {
    List<Commande> commandes = new ArrayList<>();
    String query = "SELECT "
        + "c.id AS commande_id, "
        + "p.nom AS nomproduit, "
        + "cp.quantite, "
        + "cl.nom AS nomclient, "
        + "c.statut, "
        + "c.date_commande "
        + "FROM commande_produits cp "
        + "JOIN commandes c ON cp.commande_id = c.id "
        + "JOIN produits p ON cp.produit_id = p.id "
        + "JOIN clients cl ON c.client_id = cl.id "  // Correction ici
        + "ORDER BY c.date_commande DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Commande commande = new Commande(
                rs.getInt("commande_id"),
                rs.getString("nomproduit"),
                rs.getInt("quantite"),
                rs.getString("nomclient"),
                rs.getString("statut"),
                rs.getTimestamp("date_commande").toLocalDateTime()
            );
            commandes.add(commande);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return commandes;
}

      public List<String> getAllClients() {
        String query = "SELECT nom FROM clients";
        List<String> clients = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                clients.add(resultSet.getString("nom"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des clients.", e);
        }

        return clients;
    }
      
      public void insertCommande(Commande commande) {
    String queryCommande = "INSERT INTO commandes (client_id, statut, date_commande) " +
                           "VALUES ((SELECT id FROM clients WHERE nom = ?), ?, ?) RETURNING id";
    String queryCommandeProduit = "INSERT INTO commande_produits (commande_id, produit_id, quantite) " +
                                  "VALUES (?, (SELECT id FROM produits WHERE nom = ?), ?)";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatementCommande = connection.prepareStatement(queryCommande);
         PreparedStatement preparedStatementCommandeProduit = connection.prepareStatement(queryCommandeProduit)) {

        // Insérer la commande
        preparedStatementCommande.setString(1, commande.getNomClient());
        preparedStatementCommande.setString(2, commande.getStatut());
        preparedStatementCommande.setTimestamp(3, Timestamp.valueOf(commande.getDateCommande()));

        // Récupérer l'id de la commande insérée
        ResultSet resultSetCommande = preparedStatementCommande.executeQuery();
        int commandeId = 0;
        if (resultSetCommande.next()) {
            commandeId = resultSetCommande.getInt("id");
        }

        // Insérer la relation commande_produit
        preparedStatementCommandeProduit.setInt(1, commandeId);
        preparedStatementCommandeProduit.setString(2, commande.getNomProduit());
        preparedStatementCommandeProduit.setInt(3, commande.getQuantite());

        preparedStatementCommandeProduit.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("Erreur lors de l'insertion de la commande.", e);
    }
}

        public List<String> getAllProduits() {
        String query = "SELECT nom FROM produits";
        List<String> produits = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                produits.add(resultSet.getString("nom"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la récupération des produits.", e);
        }

        return produits;
    }
      
        
        public List<Commande> searchCommandes(String produit, String client, String statut, LocalDateTime dateDebut, LocalDateTime dateFin) {
    List<Commande> commandes = new ArrayList<>();
    StringBuilder query = new StringBuilder("SELECT "
        + "c.id AS commande_id, "
        + "p.nom AS nomproduit, "
        + "cp.quantite, "
        + "cl.nom AS nomclient, "
        + "c.statut, "
        + "c.date_commande "
        + "FROM commande_produits cp "
        + "JOIN commandes c ON cp.commande_id = c.id "
        + "JOIN produits p ON cp.produit_id = p.id "
        + "JOIN clients cl ON c.client_id = cl.id "
        + "WHERE 1=1 ");

    // Ajout des conditions dynamiquement
    if (produit != null && !produit.isEmpty()) {
        query.append("AND p.nom LIKE ? ");
    }
    if (client != null && !client.isEmpty()) {
        query.append("AND cl.nom LIKE ? ");
    }
    if (statut != null && !statut.isEmpty()) {
        query.append("AND c.statut = ? ");
    }
    if (dateDebut != null) {
        query.append("AND c.date_commande >= ? ");
    }
    if (dateFin != null) {
        query.append("AND c.date_commande <= ? ");
    }

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query.toString())) {

        int paramIndex = 1;

        // Associer les valeurs des paramètres
        if (produit != null && !produit.isEmpty()) {
            stmt.setString(paramIndex++, "%" + produit + "%");
        }
        if (client != null && !client.isEmpty()) {
            stmt.setString(paramIndex++, "%" + client + "%");
        }
        if (statut != null && !statut.isEmpty()) {
            stmt.setString(paramIndex++, statut);
        }
        if (dateDebut != null) {
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(dateDebut));
        }
        if (dateFin != null) {
            stmt.setTimestamp(paramIndex++, Timestamp.valueOf(dateFin));
        }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Commande commande = new Commande(
                rs.getInt("commande_id"),
                rs.getString("nomproduit"),
                rs.getInt("quantite"),
                rs.getString("nomclient"),
                rs.getString("statut"),
                rs.getTimestamp("date_commande").toLocalDateTime()
            );
            commandes.add(commande);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return commandes;
}

      
      

}
