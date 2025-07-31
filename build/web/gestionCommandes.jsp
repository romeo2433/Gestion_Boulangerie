<%@ page import="java.util.List" %>
<%@ page import="model.Commande" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Commandes</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Gestion des Commandes</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Utilisateur</th>
                <th>Date</th>
                <th>Statut</th>
                <th>Total</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");
                if (commandes != null && !commandes.isEmpty()) {
                    for (Commande commande : commandes) {
            %>
                <tr>
                    <td><%= commande.getIdCommande() %></td>
                    <td><%= commande.getIdUtilisateur() %></td>
                    <td><%= commande.getDateCommande() %></td>
                    <td><%= commande.getStatut() %></td>
                    <td><%= commande.getTotal() %></td>
                    <td>
                        <a href="detailsCommande.jsp?id=<%= commande.getIdCommande() %>">Détails</a>
                        <form action="CommandeServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= commande.getIdCommande() %>">
                            <button type="submit">Supprimer</button>
                        </form>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="6">Aucune commande trouvée</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <button onclick="window.location.href='adminDashboard.jsp'">Retour au Tableau Admin</button>
    <button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</body>
</html>
