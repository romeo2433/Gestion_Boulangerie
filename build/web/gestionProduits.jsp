
<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" %>



<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">

    <table>
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prix</th>
                <th>Catégorie</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%-- Affichage des produits --%>
            <%
            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
            if (produits != null) {
                for (Produit produit : produits) {
            %>
                <tr>
                    <td><%= produit.getNom() %></td>
                    <td><%= produit.getPrix() %></td>
                 
                    <td><%= produit.getCategorieNom() %></td>  <!-- Utilisation du nom de la catégorie -->
                    <td>
                        <button class="btn" onclick="window.location.href='ProduitServlet?action=edit&id=<%= produit.getId() %>'">Modifier</button>
                        <button class="btn danger" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?') && window.location.href='ProduitServlet?action=delete&id=<%= produit.getId() %>';">Supprimer</button>
                    </td>
                </tr>
            <% 
                }
            } else { 
            %>
                <tr>
                    <td colspan="5">Aucun produit trouvé.</td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <!-- Boutons de navigation -->
    <button onclick="window.location.href='ProduitServlet?action=add'">Ajouter un Produit</button>
    <button onclick="window.location.href='adminDashboard.jsp'">Retour au tableau admin</button>
    <button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
    
    

 </main>
<%@ include file="./template/footer.jsp" %>