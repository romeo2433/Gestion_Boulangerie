package servlet;


import model.Utilisateur;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UtilisateurServlet")
public class UtilisateurServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action reçue : " + action);

        if ("loginClient".equals(action)) {
            handleLoginClient(request, response);
        } else if ("registerClient".equals(action)) {
            handleRegisterClient(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non reconnue.");
        }
    }

    private void handleLoginClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("Tentative de connexion client avec email : " + email);

        Utilisateur utilisateurConnecte = checkCredentials(email, password);

        if (utilisateurConnecte != null) {
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", utilisateurConnecte);
            response.sendRedirect("catalogueProduits.jsp"); // Redirection vers le catalogue
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/loginClient.jsp").forward(request, response);
        }
    }

    private void handleRegisterClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (nom == null || nom.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont requis.");
            request.getRequestDispatcher("/inscriptionClient.jsp").forward(request, response);
            return;
        }

        try {
            addClientToDatabase(nom, email, password);
            response.sendRedirect("loginClient.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'enregistrement. Veuillez réessayer.");
            request.getRequestDispatcher("/inscriptionClient.jsp").forward(request, response);
        }
    }

    private Utilisateur checkCredentials(String email, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT id, nom, email, role FROM utilisateurs WHERE email = ? AND mot_de_passe = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Utilisateur(
                            resultSet.getInt("id"),
                            resultSet.getString("nom"),
                            resultSet.getString("email"),
                            password,
                            resultSet.getString("role")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addClientToDatabase(String nom, String email, String password) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            String query = "INSERT INTO utilisateurs (nom, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, nom);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, "Client");

                statement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }
}
