package servlet;

import controller.CategorieController;
import controller.ProduitController;
import model.Categorie;
import controller.IngredientController;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ingredient;
import model.Produit;
import model.Type;

@WebServlet("/ProduitServlet")
public class ProduitServlet extends HttpServlet {
    private final ProduitController produitController = new ProduitController();
    private final CategorieController categorieController = new CategorieController();
    private final IngredientController ingredientController = new IngredientController();
     private static final long serialVersionUID = 1L;
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");

    if (action == null) action = "list";

    switch (action) {
    case "add":
        List<Categorie> categories = categorieController.getAllCategories();
        request.setAttribute("categories", categories); // Ajouter les catégories pour la vue
        request.getRequestDispatcher("/ajouterProduit.jsp").forward(request, response);
        break;

    case "edit":
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("produit", produitController.getProduitById(id));
        request.setAttribute("categories", categorieController.getAllCategories()); // Ajouter les catégories
        request.getRequestDispatcher("/modifierProduit.jsp").forward(request, response);
        break;

    case "delete":
        int deleteId = Integer.parseInt(request.getParameter("id"));
        produitController.deleteProduit(deleteId);
        response.sendRedirect("ProduitServlet");
        break;

    default:
        // Récupérer tous les produits
        List<Produit> produits = produitController.getAllProduits();
        request.setAttribute("produits", produits);

        // Récupérer la liste des catégories pour la liste déroulante
        List<Categorie> allCategories = categorieController.getAllCategories();
        request.setAttribute("categories", allCategories);

        // Récupérer la liste des ingrédients pour la liste déroulante
        List<Ingredient> ingredients = ingredientController.getAllIngredients();
        request.setAttribute("ingredients", ingredients);

        // Récupérer la liste des types
        List<Type> types = produitController.getAllTypes();
        request.setAttribute("types", types);

        // Gérer le filtrage des produits par type si nécessaire
        String typeIdStr = request.getParameter("typeId");
        if (typeIdStr != null && !typeIdStr.isEmpty()) {
            int typeId = Integer.parseInt(typeIdStr);
            // Appliquez un filtre aux produits si nécessaire (ajustez selon vos besoins)
            List<Produit> produitsFiltres = produitController.getProduitsByType(typeId);
            request.setAttribute("produits", produitsFiltres);
        }

        // Rediriger vers la JSP pour afficher tous les produits
        request.getRequestDispatcher("/gestionProduits.jsp").forward(request, response);
        break;
}

}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            produitController.addProduit(
                request.getParameter("nom"),
                request.getParameter("description"),
                Double.parseDouble(request.getParameter("prix")),
                Integer.parseInt(request.getParameter("categorieId"))
            );
        } else if ("edit".equals(action)) {
            produitController.updateProduit(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("nom"),
                request.getParameter("description"),
                Double.parseDouble(request.getParameter("prix")),
                Integer.parseInt(request.getParameter("categorieId"))
            );
        }

        response.sendRedirect("ProduitServlet");
    }
}