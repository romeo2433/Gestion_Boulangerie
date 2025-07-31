/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.IngredientController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ingredient;
import model.Produit;
import controller.ProduitController;
import controller.RecetteController;
import model.Recette;



/**
 *
 * @author Romeo
 */@WebServlet(name = "recetteServlet", urlPatterns = {"/recetteServlet"})
public class RecetteServlet extends HttpServlet {

    private final ProduitController produitController = new ProduitController();
    private final IngredientController ingredientController = new IngredientController();
    private final RecetteController recetteController = new RecetteController(); // On ajoute un contrôleur pour les recettes

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la liste des produits et des ingrédients depuis les contrôleurs
        List<Produit> produits = produitController.getAllProduits();
        List<Ingredient> ingredients = ingredientController.getAllIngredients();
        List<Recette> recettes = recetteController.getAllRecettes();

        // Passer les données à la JSP
        request.setAttribute("produits", produits);
        request.setAttribute("ingredients", ingredients);
         request.setAttribute("recettes", recettes);

        // Rediriger vers la JSP
        request.getRequestDispatcher("/recettes.jsp").forward(request, response);
    }

 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Récupérer les paramètres du formulaire
    String produitIdParam = request.getParameter("produitId");
    String ingredientIdParam = request.getParameter("ingredientId");
    String quantiteParam = request.getParameter("quantite");

    // Convertir les paramètres en entiers ou doubles
    int produitId = (produitIdParam != null && !produitIdParam.isEmpty()) ? Integer.parseInt(produitIdParam) : 0;
    int ingredientId = (ingredientIdParam != null && !ingredientIdParam.isEmpty()) ? Integer.parseInt(ingredientIdParam) : 0;
    double quantite = Double.parseDouble(quantiteParam);

    // Afficher les valeurs pour vérifier si elles sont correctes
    System.out.println("produitId: " + produitId + ", ingredientId: " + ingredientId + ", quantite: " + quantite);

    // Vérifier si les sélections sont vides
    if (produitId == 0 && ingredientId == 0) {
        request.setAttribute("errorMessage", "Veuillez sélectionner un produit ou un ingrédient.");
        // Récupérer la liste des produits et des ingrédients depuis les contrôleurs
        List<Produit> produits = produitController.getAllProduits();
        List<Ingredient> ingredients = ingredientController.getAllIngredients();
        
        // Passer les données à la JSP
        request.setAttribute("produits", produits);
        request.setAttribute("ingredients", ingredients);
        
        // Rediriger vers la JSP avec le message d'erreur
        request.getRequestDispatcher("/recettes.jsp").forward(request, response);
        return;  // Stoppe l'exécution du reste du code
    }

    // Vérifier quel élément est sélectionné (produit ou ingrédient)
    if (produitId > 0 && ingredientId > 0) {
        recetteController.ajouterRecette(produitId, ingredientId, quantite); // Ajouter un produit
    } 

    List<Produit> produits = produitController.getAllProduits();
        List<Ingredient> ingredients = ingredientController.getAllIngredients();
        List<Recette> recettes = recetteController.getAllRecettes();

        // Passer les données à la JSP
        request.setAttribute("produits", produits);
        request.setAttribute("ingredients", ingredients);
         request.setAttribute("recettes", recettes);

        // Rediriger vers la JSP
        request.getRequestDispatcher("/recettes.jsp").forward(request, response);
}



    @Override
    public String getServletInfo() {
        return "Ce servlet gère les recettes et leur composition.";
    }
}
