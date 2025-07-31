package servlet;

import controller.StockController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.StockIngredient;
import model.StockProduit;

@WebServlet(name = "stockservlet", urlPatterns ={"/stockservlet"})
public class StockServlet extends HttpServlet {
    private StockController stockController;

    @Override
    public void init() throws ServletException {
        try {
            // Initialisation du StockController
            stockController = new StockController();
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la connexion à la base de données.", e);
        }
    }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupération des produits en stock
        List<StockProduit> stockProduits = stockController.getAllStockProduits();

        // Récupération des ingrédients en stock via le contrôleur
        List<StockIngredient> stockIngredients = stockController.getStocksIngredients();

        // Vérification des produits en stock
        if (stockProduits == null || stockProduits.isEmpty()) {
            request.setAttribute("messageProduits", "Aucun produit en stock.");
        } else {
            request.setAttribute("stockProduits", stockProduits);
        }

        // Vérification des ingrédients en stock
        if (stockIngredients == null || stockIngredients.isEmpty()) {
            request.setAttribute("messageIngredients", "Aucun ingrédient en stock.");
        } else {
            request.setAttribute("stockIngredients", stockIngredients);
        }

        // Redirection vers la JSP
        request.getRequestDispatcher("/gestionStocks.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Rediriger les requêtes POST vers la méthode doGet (si nécessaire)
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet pour la gestion du stock";
    }
}
