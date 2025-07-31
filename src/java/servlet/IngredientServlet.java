package servlet;

import controller.IngredientController;

import model.Ingredient;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Unite;


@WebServlet("/IngredientServlet")
public class IngredientServlet extends HttpServlet {
    private IngredientController ingredientController;

    @Override
    public void init() throws ServletException {
        ingredientController = new IngredientController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ingredient> ingredients = ingredientController.getAllIngredients();
        List<Unite> unites = ingredientController.getAllUnites();
        request.setAttribute("ingredients", ingredients);
        request.setAttribute("unites", unites);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gestionIngredients.jsp");
        dispatcher.forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    try {
        if ("add".equals(action)) {
            // Récupération des paramètres
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            double seuil = Double.parseDouble(request.getParameter("seuil"));
            int uniteId = Integer.parseInt(request.getParameter("uniteId"));

            // Appel du contrôleur pour ajouter un ingrédient
            ingredientController.addIngredient(nom, prix, seuil, uniteId);
            response.sendRedirect("IngredientServlet?message=success_add");

        } else if ("delete".equals(action)) {
            // Récupération de l'ID à supprimer
            int id = Integer.parseInt(request.getParameter("id"));

            // Appel du contrôleur pour supprimer un ingrédient
            ingredientController.deleteIngredient(id);
            response.sendRedirect("IngredientServlet?message=success_delete");

        } else if ("edit".equals(action)) {
            // Récupération des paramètres pour la mise à jour
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            double prix = Double.parseDouble(request.getParameter("prix"));
            double seuil = Double.parseDouble(request.getParameter("seuil"));
            int uniteId = Integer.parseInt(request.getParameter("uniteId"));

            // Appel du contrôleur pour mettre à jour l'ingrédient
            ingredientController.updateIngredient(id, nom, prix, seuil, uniteId);
            response.sendRedirect("IngredientServlet?message=success_edit");

        } else {
            // Action non reconnue
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action non reconnue.");
        }
    } catch (NumberFormatException e) {
        // Gestion des erreurs de format de nombre
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres numériques.");
    } catch (Exception e) {
        // Gestion générale des erreurs
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Une erreur est survenue.");
    }
}

}
