/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import controller.ProduitMoisController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mois;
import model.Produit;

/**
 *
 * @author Romeo
 */@WebServlet("/RechercheProduitMoisServlet")
public class RechercheProduitMoisServlet extends HttpServlet {

    private final ProduitMoisController produitMoisController = new ProduitMoisController();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer l'ID du mois depuis la requête
       List<Mois> moisList = produitMoisController.getAllMois();
        int moisId = 0;
         List<Produit> produits = produitMoisController.getProduitsByMois(moisId);
        // Passer les données à la vue (JSP)
        request.setAttribute("mois", moisList);
        // Rediriger vers la page JSP pour afficher les résultats
        request.getRequestDispatcher("/afficher_produits_par_mois.jsp").forward(request, response);
    }
}
