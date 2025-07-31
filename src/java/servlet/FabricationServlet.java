package servlet;

import controller.FabricationController;
import controller.RecetteController;
import controller.ProduitController;
import model.Recette;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produit;

@WebServlet("/FabricationServlet")
public class FabricationServlet extends HttpServlet {
    private RecetteController recetteController;
   ProduitController produitController = new ProduitController();

    @Override
    public void init() throws ServletException {
        recetteController = new RecetteController();
        produitController = new ProduitController();
    }

 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Produit> produits = produitController.getAllProduits();
    System.out.println("Nombre de produits : " + produits.size());  // Debug: affichez la taille de la liste de produits
    // Assurez-vous que les produits sont bien passés à la JSP
    request.setAttribute("produits", produits);
    request.getRequestDispatcher("fabriquerProduit.jsp").forward(request, response);
}


    
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    String produitIdParam = request.getParameter("produitId");
    String quantiteParam = request.getParameter("quantite");

    try {
        int produitId = Integer.parseInt(produitIdParam);
        int quantiteFabriquee = Integer.parseInt(quantiteParam);

        String url = "jdbc:postgresql://localhost:5432/boulangerie";
        String user = "postgres";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            FabricationController controller = new FabricationController();

            if(controller.verifierRecette(connection,produitId)){
            if (!controller.verifierStocks(connection, produitId, quantiteFabriquee)) {
                request.setAttribute("message", "Stock insuffisant pour fabriquer ce produit.");
                request.setAttribute("success", false);
                request.getRequestDispatcher("fabriquerProduit.jsp").forward(request, response);
                return;
            }
             controller.mettreAJourStocks(connection, produitId, quantiteFabriquee);
            controller.insererStockProduit(connection, produitId, quantiteFabriquee);
            request.setAttribute("message", "Produit fabriqué avec succès !");
             request.setAttribute("success", true);
            }else{
                request.setAttribute("erreur", "Pas de recettes pour ce produit!");
            }

           

            
           
        } catch (SQLException e) {
            log("Erreur SQL lors de la fabrication", e); // Ajout au fichier de log
            request.setAttribute("message", "Erreur technique. Veuillez réessayer plus tard.");
            request.setAttribute("success", false);
        }

    } catch (NumberFormatException e) {
        request.setAttribute("message", "Entrée invalide : Veuillez entrer des valeurs numériques valides.");
        request.setAttribute("success", false);
    }
        List<Produit> produits = produitController.getAllProduits();
    System.out.println("Nombre de produits : " + produits.size());  // Debug: affichez la taille de la liste de produits

    // Assurez-vous que les produits sont bien passés à la JSP
    request.setAttribute("produits", produits);
 request.getRequestDispatcher("fabriquerProduit.jsp").forward(request, response);
}

}
