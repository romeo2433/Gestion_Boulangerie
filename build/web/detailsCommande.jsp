<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Détails de la Commande</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Détails de la Commande</h1>
    <p>Client : <%= commande.getClientNom() %></p>
    <p>Date : <%= commande.getDate() %></p>
    <p>Statut : <%= commande.getStatut() %></p>
    <h2>Produits :</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Quantité</th>
                <th>Prix Unitaire</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <% 
                Commande commande = (Commande) request.getAttribute("commande");
                if (commande != null && commande.getLignes() != null) {
                    for (Ligne ligne : commande.getLignes()) {
            %>
                <tr>
                    <td><%= ligne.getProduitNom() %></td>
                    <td><%= ligne.getQuantite() %></td>
                    <td><%= ligne.getPrixUnitaire() %></td>
                    <td><%= ligne.getTotal() %></td>
                </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
    <button onclick="window.location.href='gestionCommandes.jsp'">Retour</button>
    
           <br>
    <button onclick="window.location.href='gestionCommandes.jsp'">Retour</button>
<button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</body>
</html>
