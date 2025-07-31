package servlet;

import controller.ProduitController;
import controller.RecommandationController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Produit;

@WebServlet("/InsertionRecommandationServlet")
public class InsertionRecommandationServlet extends HttpServlet {
    private final RecommandationController recommandationController = new RecommandationController();
       ProduitController produitController = new ProduitController();
    
     @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Produit> produits = produitController.getAllProduits();
    System.out.println("Nombre de produits : " + produits.size());  // Debug: affichez la taille de la liste de produits

    // Assurez-vous que les produits sont bien passés à la JSP
    request.setAttribute("produits", produits);
    request.getRequestDispatcher("insertion_recommandation.jsp").forward(request, response);
}
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        int idProduit = Integer.parseInt( request.getParameter("idProduit"));

        // Insérer la recommandation
        boolean success = recommandationController.addRecommandation( idProduit, dateDebut, dateFin);

        // Rediriger selon le résultat
        if (success) {
            response.sendRedirect("AfficherRecommandationsServlet");
        } else {
            response.sendRedirect("insertion_recommandation.jsp?error=Erreur lors de l'insertion");
        }
    }
}
