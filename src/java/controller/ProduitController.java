package controller;

import model.Produit;
import model.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.StockProduit;
import model.Type;


import static util.DatabaseConnection.getConnection;

public class ProduitController {

    
    
             private static final String URL = "jdbc:postgresql://localhost:5432/boulangerie";  // Remplacez par votre URL
    private static final String USER = "postgres";  // Remplacez par votre utilisateur
    private static final String PASSWORD = "root";  // Remplacez par votre mot de passe

    public List<Categorie> getAllCategories() {
        List<Categorie> categories = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM categories";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categorie categorie = new Categorie(rs.getInt("id"), rs.getString("nom"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

  public List<Produit> getAllProduits() {
    List<Produit> produits = new ArrayList<>();
    String query = "SELECT p.id, p.nom, p.description, p.prix, p.categorie_id, c.nom AS categorie_nom " +
                   "FROM produits p " +
                   "JOIN categories c ON p.categorie_id = c.id";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            produits.add(new Produit(
                rs.getInt("id"),
                rs.getString("nom"),
                    rs.getDouble("prix"),
                    rs.getString("categorie_nom")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return produits;
}


    public void addProduit(String nom, String description, double prix,  int categorieId) {
       String query = "INSERT INTO produits (nom, description, prix, categorie_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nom);
            stmt.setString(2, description);
            stmt.setDouble(3, prix);
            stmt.setInt(4, categorieId); // Correction ici : passez de `5` à `4`
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduit(int id, String nom, String description, double prix, int categorieId) {
        String query = "UPDATE produits SET nom = ?, description = ?, prix = ?,  categorie_id = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

                    stmt.setString(1, nom);
                    stmt.setString(2, description);
                    stmt.setDouble(3, prix);
                    stmt.setInt(4, categorieId); // Correct : Index 4 pour categorie_id
                    stmt.setInt(5, id);          // Correct : Index 5 pour id

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduit(int id) {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            String deleteRecettes = "DELETE FROM recettes WHERE produit_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteRecettes)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            String deleteDetailsCommandes = "DELETE FROM details_commandes WHERE produit_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteDetailsCommandes)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            String deleteProduit = "DELETE FROM produits WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteProduit)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produit getProduitById(int id) {
        String query = "SELECT p.id, p.nom, p.description, p.prix, p.categorie_id, c.nom AS categorie_nom " +
                       "FROM produits p " +
                       "JOIN categories c ON p.categorie_id = c.id WHERE p.id = ?";
        Produit produit = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produit = new Produit(
                    rs.getInt("id"),
                    rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produit;
    }
    
    
  public List<Produit> getProduitsByIngredient(int ingredientId) {
    List<Produit> produits = new ArrayList<>();
    String query = "SELECT p.id, p.nom, p.prix FROM produits p " +
                   "JOIN recettes r ON p.id = r.produit_id " +
                   "WHERE r.ingredient_id = ?";
    
    System.out.println("ID d'ingrédient utilisé : " + ingredientId);

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, ingredientId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                double prix = rs.getDouble("prix");
                produits.add(new Produit(id, nom));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    System.out.println("Produits trouvés dans la méthode : " + produits);
    return produits;
}
public List<Type> getAllTypes() {
    String query = "SELECT idtype, nom FROM type"; // Requête SQL
    List<Type> types = new ArrayList<>();

    String jdbcUrl = "jdbc:postgresql://localhost:5432/boulangerie";
    String dbUser = "postgres";
    String dbPassword = "root";

    try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Type type = new Type();
            type.setId(rs.getInt("idtype"));  // Récupère l'idtype
            type.setNom(rs.getString("nom")); // Récupère le nom
            types.add(type); // Ajouter le type à la liste
        }
    } catch (SQLException e) {
        System.err.println("Erreur lors de la récupération des types : " + e.getMessage());
    }

    return types;
}



    

    public List<StockProduit> getAllStockProduits() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Produit> getProduitsByType(int typeId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void getString(String categorie_nom) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}