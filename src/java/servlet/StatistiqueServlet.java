package servlet;

import controller.VenteController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/StatistiqueServlet")
public class StatistiqueServlet extends HttpServlet {
    private final VenteController venteController = new VenteController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer le montant total des ventes par sexe
        Map<String, Double> montantParSexe = venteController.getMontantTotalParSexe();

        // Passer les données à la JSP
        request.setAttribute("montantParSexe", montantParSexe);

        // Rediriger vers la page de statistiques
        request.getRequestDispatcher("statistiques.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Si nécessaire, vous pouvez gérer les requêtes POST ici
        doGet(request, response);
    }
}