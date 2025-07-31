package controller;

import model.Ingredient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.StockIngredient;
import model.Unite;

public class IngredientController {
    private static final String URL = "jdbc:postgresql://localhost:5432/boulangerie";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "root"; 

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

  public List<Ingredient> getAllIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    String query = "SELECT i.id AS ingredient_id, i.nom AS ingredient_nom, i.prix_unitaire, i.seuil_minimum, " +
                   "u.id AS unite_id, u.nom AS unite_nom " +
                   "FROM ingredients i " +
                   "JOIN unite u ON i.unite_id = u.id";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int ingredientId = rs.getInt("ingredient_id");
            String ingredientNom = rs.getString("ingredient_nom");
            double prixUnitaire = rs.getDouble("prix_unitaire");
            double seuilMinimum = rs.getDouble("seuil_minimum");

            int uniteId = rs.getInt("unite_id");
            String uniteNom = rs.getString("unite_nom");

            // Créez l'objet Unite
            Unite unite = new Unite(uniteId, uniteNom);

            // Créez l'objet Ingredient avec l'unité associée
            Ingredient ingredient = new Ingredient(ingredientId, ingredientNom, prixUnitaire, seuilMinimum);

            // Ajoutez l'ingrédient à la liste
            ingredients.add(ingredient);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return ingredients;
}

public void addIngredient(String nom, double prix, double seuil, int uniteId) {
    String query = "INSERT INTO ingredients (nom, prix_unitaire, seuil_minimum, unite_id) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, nom);
        stmt.setDouble(2, prix);
        stmt.setDouble(3, seuil);
        stmt.setInt(4, uniteId);

        System.out.println("Requête préparée : " + query);
        stmt.executeUpdate();
        System.out.println("Ingrédient ajouté avec succès.");

    } catch (SQLException e) {
        System.err.println("Erreur lors de l'ajout de l'ingrédient : " + e.getMessage());
        e.printStackTrace();
    }
}


    public void deleteIngredient(int id) {
        String query = "DELETE FROM ingredients WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Nouvelle méthode pour mettre à jour un ingrédient
   public void updateIngredient(int id, String nom, double prixUnitaire, double seuilMinimum, int uniteId) {
    String query = "UPDATE ingredients SET nom = ?, prix_unitaire = ?, seuil_minimum = ?, unite_id = ? WHERE id = ?";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, nom);
        stmt.setDouble(2, prixUnitaire);
        stmt.setDouble(3, seuilMinimum);
        stmt.setInt(4, uniteId);
        stmt.setInt(5, id);

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Ingrédient mis à jour avec succès.");
        } else {
            System.out.println("Aucun ingrédient trouvé avec l'ID spécifié.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
    public List<Unite> getAllUnites() {
    List<Unite> unites = new ArrayList<>();
    String query = "SELECT * FROM unite";

    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            unites.add(new Unite(id, nom));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return unites;
}
    
    public Ingredient getIngredientById(int id) {
    // Votre logique pour récupérer un ingredient par ID
    // Exemple avec une requête SQL :
    String query = "SELECT * FROM ingredients WHERE id = ?";
    Ingredient ingredient = null;
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(query)) {
        
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idIngredient = rs.getInt("id");
            String nom = rs.getString("nom");
            double prixUnitaire = rs.getDouble("prix_unitaire");
            double seuilMinimum = rs.getDouble("seuil_minimum");
            // Créez l'objet Ingredient avec les données récupérées
            ingredient = new Ingredient(idIngredient, nom, prixUnitaire, seuilMinimum);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ingredient;
}





    public List<StockIngredient> getAllStockIngredients() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
