package servlet;

import controller.CommandeController;
import model.Commande;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {
    private CommandeController commandeController;

    @Override
    public void init() throws ServletException {
        commandeController = new CommandeController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les commandes, produits, et clients
        List<Commande> commandes = commandeController.getAllCommandes();
        List<String> produits = commandeController.getAllProduits();
        List<String> clients = commandeController.getAllClients();

        // Passer les données à la JSP
        request.setAttribute("commandes", commandes);
        request.setAttribute("produits", produits);
        request.setAttribute("clients", clients);

        // Rediriger vers la page JSP
        request.getRequestDispatcher("commandes.jsp").forward(request, response);
    }

   @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Déterminez l'action (insertion ou recherche)
    String action = request.getParameter("action");

    if ("insert".equals(action)) {
        // Gestion de l'insertion de commande
        String nomProduit = request.getParameter("nomProduit");
        int quantite = Integer.parseInt(request.getParameter("quantite"));
        String nomClient = request.getParameter("nomClient");
        String statut = request.getParameter("statut");
        LocalDateTime dateCommande = LocalDateTime.parse(request.getParameter("dateCommande"));

        // Créer une nouvelle commande
        Commande commande = new Commande(0, nomProduit, quantite, nomClient, statut, dateCommande);

        // Insérer la commande
        try {
            commandeController.insertCommande(commande);
            // Rediriger après insertion réussie
            response.sendRedirect(request.getContextPath() + "/commandes");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de l'insertion de la commande.");
            doGet(request, response); // Réafficher le formulaire avec le message d'erreur
        }
    } else if ("search".equals(action)) {
        // Gestion de la recherche multicritère
        String produit = request.getParameter("produit");
        String client = request.getParameter("client");
        String statut = request.getParameter("statut");
        String dateDebutStr = request.getParameter("dateDebut");
        String dateFinStr = request.getParameter("dateFin");

        LocalDateTime dateDebut = (dateDebutStr != null && !dateDebutStr.isEmpty())
            ? LocalDateTime.parse(dateDebutStr) : null;
        LocalDateTime dateFin = (dateFinStr != null && !dateFinStr.isEmpty())
            ? LocalDateTime.parse(dateFinStr) : null;

        // Exécuter la recherche
        List<Commande> commandes = commandeController.searchCommandes(produit, client, statut, dateDebut, dateFin);

        // Passer les résultats à la JSP
        request.setAttribute("commandes", commandes);
        request.getRequestDispatcher("commandes.jsp").forward(request, response);
    }
}

}
