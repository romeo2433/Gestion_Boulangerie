package servlet;

import model.Produit;
import model.Mois;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AfficherSelectionServlet")
public class AfficherSelectionServlet extends HttpServlet {
    private static final String URL = "jdbc:postgresql://localhost:5432/boulangerie";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String[]> selections = new ArrayList<>();
        String sql = "SELECT p.nom AS produit_nom, m.mois_nom AS mois_nom " +
                     "FROM produit_mois_selection ps " +
                     "JOIN produits p ON ps.produit_id = p.id " +
                     "JOIN mois m ON ps.mois_id = m.id";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String produitNom = resultSet.getString("produit_nom");
                String moisNom = resultSet.getString("mois_nom");
                selections.add(new String[]{produitNom, moisNom});
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'erreur
        }

        // Passer les données à la JSP
        request.setAttribute("selections", selections);
        request.getRequestDispatcher("/afficher_selections.jsp").forward(request, response);
    }
}
