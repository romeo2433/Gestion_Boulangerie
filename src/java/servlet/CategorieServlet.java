package servlet;

import controller.CategorieController;

import model.Categorie;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CategorieServlet")
public class CategorieServlet extends HttpServlet {
    private CategorieController categorieController;

    @Override
    public void init() throws ServletException {
        categorieController = new CategorieController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categorie> categories = categorieController.getAllCategories();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("gestionCategories.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        boolean success = false;

        if ("add".equals(action)) {
            String nom = request.getParameter("nom");
            success = categorieController.addCategorie(nom);

        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            success = categorieController.editCategorie(id, nom);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            success = categorieController.deleteCategorie(id);
        }

        if (!success) {
            request.setAttribute("error", "Une erreur est survenue.");
        }

        response.sendRedirect("CategorieServlet");
    }
}
