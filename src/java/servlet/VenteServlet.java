package servlet;

import controller.ProduitController;
import controller.VenteController;
import model.Vente;
import model.Produit;
import model.Vendeur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/VenteServlet")
public class VenteServlet extends HttpServlet {
    private final VenteController venteController = new VenteController();
    private final ProduitController produitController = new ProduitController();

 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Récupérer les paramètres de recherche
    String sexe = request.getParameter("sexe");
    String dateDebutStr = request.getParameter("dateDebut");
    String dateFinStr = request.getParameter("dateFin");

    LocalDate dateDebut = null;
    LocalDate dateFin = null;

    // Convertir les dates en LocalDate
    if (dateDebutStr != null && !dateDebutStr.isEmpty()) {
        dateDebut = LocalDate.parse(dateDebutStr, DateTimeFormatter.ISO_LOCAL_DATE);
    }
    if (dateFinStr != null && !dateFinStr.isEmpty()) {
        dateFin = LocalDate.parse(dateFinStr, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    // Récupérer les ventes filtrées
    List<Vente> ventes = venteController.getVentesFiltrees(sexe, dateDebut, dateFin);

    // Récupérer tous les produits et vendeurs
    List<Produit> produits = produitController.getAllProduits();
    List<Vendeur> vendeurs = venteController.getAllVendeurs();

    // Passer les données à la JSP
    request.setAttribute("ventes", ventes);
    request.setAttribute("produits", produits);
    request.setAttribute("vendeurs", vendeurs);
    request.setAttribute("sexes", Arrays.asList("Homme", "Femme", "Autre")); // Liste des sexes disponibles

    // Rediriger vers la page de vente
    request.getRequestDispatcher("vente.jsp").forward(request, response);
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Récupérer les informations du formulaire
    int produitId = Integer.parseInt(request.getParameter("produit_id"));
    int quantite = Integer.parseInt(request.getParameter("quantite"));
    int vendeurId = Integer.parseInt(request.getParameter("vendeur_id"));
    String dateVenteStr = request.getParameter("date_vente"); // Récupérer la date de vente

    // Convertir la date de vente en LocalDateTime
    LocalDateTime dateVente = LocalDateTime.parse(dateVenteStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    // Effectuer la vente
    String message = venteController.effectuerVente(produitId, quantite, vendeurId, dateVente);

    // Passer un message de succès ou d'erreur à la JSP
    request.setAttribute("message", message);

    // Récupérer à nouveau les données pour affichage
    List<Vente> ventes = venteController.getAllVentes();
    List<Produit> produits = produitController.getAllProduits();
    List<Vendeur> vendeurs = venteController.getAllVendeurs();

    // Passer les données à la JSP
    request.setAttribute("ventes", ventes);
    request.setAttribute("produits", produits);
    request.setAttribute("vendeurs", vendeurs);
    request.setAttribute("sexes", Arrays.asList("Homme", "Femme", "Autre")); // Liste des sexes disponibles

    // Rediriger vers la page de vente
    request.getRequestDispatcher("vente.jsp").forward(request, response);
}
}