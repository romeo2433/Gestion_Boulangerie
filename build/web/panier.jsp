<%@ page import="java.util.List" %>
<%@ page import="model.PanierItem" %>  <!-- Si PanierItem est dans le package model -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panier</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Votre Panier</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Quantité</th>
                <th>Prix Unitaire</th>
                <th>Total</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<PanierItem> panier = (List<PanierItem>) request.getAttribute("panier");
                double panierTotal = 0;  // Définir la variable panierTotal avant la boucle
                if (panier != null) {
                    for (PanierItem item : panier) {
            %>
                <tr>
                    <td><%= item.getProduitNom() %></td>
                    <td><%= item.getQuantite() %></td>
                    <td><%= item.getPrixUnitaire() %></td>
                    <td><%= item.getTotal() %></td>
                    <td>
                        <form action="PanierServlet" method="post">
                            <input type="hidden" name="produitId" value="<%= item.getProduitId() %>">
                            <input type="hidden" name="action" value="remove">
                            <button type="submit">Retirer</button>
                        </form>
                    </td>
                </tr>
            <% 
                        panierTotal += item.getTotal();
                    }
                }
            %>
        </tbody>
    </table>
    <p>Total : <%= panierTotal %></p>
    <form action="CommandeServlet" method="post">
        <button type="submit">Valider la Commande</button>
    </form>
    <br>
    <button onclick="window.location.href='catalogueProduits.jsp'">Retour au Client</button>
    <button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</body>
</html>
