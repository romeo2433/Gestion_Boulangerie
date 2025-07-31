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

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Action reçue : " + action);

        if ("login".equals(action)) {
            handleLogin(request, response);
        } else if ("register".equals(action)) {
            handleRegister(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non reconnue.");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("Tentative de connexion avec email : " + email);

        Utilisateur utilisateurConnecte = checkCredentials(email, password);

        if (utilisateurConnecte != null) {
            // Connexion réussie
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", utilisateurConnecte);
            response.sendRedirect("adminDashboard.jsp");
        } else {
            // Connexion échouée
            request.setAttribute("error", "Email ou mot de passe incorrect.");
            request.getRequestDispatcher("/loginAdmin.jsp").forward(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Vérification des champs requis
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Tous les champs sont requis.");
            request.getRequestDispatcher("/inscriptionAdmin.jsp").forward(request, response);
            return;
        }

        try {
            // Ajout dans la base de données
            addUtilisateurToDatabase(name, email, password);
            response.sendRedirect("loginAdmin.jsp"); // Rediriger après succès
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'enregistrement. Veuillez réessayer.");
            request.getRequestDispatcher("/inscriptionAdmin.jsp").forward(request, response);
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
        return null; // Utilisateur non trouvé
    }

    private void addUtilisateurToDatabase(String name, String email, String password) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            String query = "INSERT INTO utilisateurs (nom, email, mot_de_passe, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, "Administrateur");

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
