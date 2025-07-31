/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Mois;
import model.Produit;

/**
 *
 * @author Romeo
 */
public class ProduitMoisController {
       private static final String URL = "jdbc:postgresql://localhost:5432/boulangerie";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    // Méthode pour récupérer tous les mois
    public List<Mois> getAllMois() {
        List<Mois> moisList = new ArrayList<>();
        String sql = "SELECT * FROM mois"; // Requête pour obtenir tous les mois

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String moisNom = resultSet.getString("mois_nom");
                moisList.add(new Mois(id, moisNom));
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Gérer l'erreur selon vos besoins
        }

        return moisList;
    }

   public List<Produit> getProduitsByMois(int moisId) {
    List<Produit> produitsList = new ArrayList<>();
    String sql = "SELECT p.id, p.nom FROM produits p " +
                 "JOIN produit_mois_selection pms ON p.id = pms.produit_id " +
                 "WHERE pms.mois_id = ?";  // Vérifie le mois_id dans la table de liaison

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, moisId);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                produitsList.add(new Produit(id, nom));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return produitsList;
}

}
