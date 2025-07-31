<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, model.Produit" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catalogue des Produits</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Catalogue des Produits</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Prix</th>
                <th>Quantité Stock</th>
                <th>Catégorie</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Produit> produits = (List<Produit>) request.getAttribute("produits");
                if (produits != null) {
                    for (Produit produit : produits) {
            %>
                <tr>
                    <td><%= produit.getNom() %></td>
                    <td><%= produit.getPrix() %> €</td>
                    <td><%= produit.getQuantiteStock() %></td>
                    <td><%= produit.getCategorieNom() != null ? produit.getCategorieNom() : "Non spécifié" %></td>
                    <td>
                        <form action="PanierServlet" method="post">
                            <input type="hidden" name="produitId" value="<%= produit.getIdProduit() %>">
                            <label for="quantite_<%= produit.getIdProduit() %>">Quantité :</label>
                            <input type="number" name="quantite" id="quantite_<%= produit.getIdProduit() %>" value="1" min="1">
                            <button type="submit">Ajouter au Panier</button>
                        </form>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="5">Aucun produit disponible.</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <button onclick="window.location.href='panier.jsp'">Voir le Panier</button>
    
       <br>
<button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</body>
</html>
