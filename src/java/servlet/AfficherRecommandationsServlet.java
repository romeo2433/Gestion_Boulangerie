package servlet;

import controller.RecommandationController;
import model.Recommandation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AfficherRecommandationsServlet")
public class AfficherRecommandationsServlet extends HttpServlet {
    private final RecommandationController recommandationController = new RecommandationController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer toutes les recommandations
        List<Recommandation> recommandations = recommandationController.getAllRecommandations();

        // Envoyer les données à la page JSP
        request.setAttribute("recommandations", recommandations);

        // Rediriger vers la page JSP
        request.getRequestDispatcher("/afficher_recommandations.jsp").forward(request, response);
    }
}
