package servlet;

import controller.ProduitController;
import controller.ProduitMoisController;
import model.Mois;
import model.Produit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ProduitMoisServlet")
public class ProduitMoisServlet extends HttpServlet {
     private final ProduitController produitController = new ProduitController();
      private final ProduitMoisController produitMoisController = new ProduitMoisController();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialiser le contrôleur
      List<Produit> produits = produitController.getAllProduits();
        // Récupérer les produits et les mois
       request.setAttribute("produits", produits);
       
        // Récupérer les mois
        List<Mois> moisList = produitMoisController.getAllMois();

        // Passer les données à la vue (JSP)
        request.setAttribute("mois", moisList);       

        // Configurer la réponse
     request.getRequestDispatcher("/insertion_produit.jsp").forward(request, response);
}
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Récupérer les valeurs sélectionnées depuis le formulaire
    String produitId = request.getParameter("produitId");
    String moisId = request.getParameter("moisId");

    // Insérer les choix dans la table produit_mois_selection
    try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/boulangerie", "postgres", "root");
         PreparedStatement statement = connection.prepareStatement("INSERT INTO produit_mois_selection (produit_id, mois_id) VALUES (?, ?)")) {

        // Associer les valeurs récupérées
        statement.setInt(1, Integer.parseInt(produitId));
        statement.setInt(2, Integer.parseInt(moisId));
        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); // Gérer les erreurs
    }

    // Rediriger vers une page de confirmation ou retourner au formulaire
    response.sendRedirect("ProduitMoisServlet");
}

}
