<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ page import="model.Mois" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="css/style.css">
    <title>Choisir Produit et Mois</title>
</head>
<body>
    <h2>Choisissez un Produit du Mois</h2>

    <form action="ProduitMoisServlet" method="post">
        <!-- Menu déroulant des produits -->
        <label for="produitId">Produit :</label>
        <select name="produitId" id="produitId" required>
            <c:forEach var="produit" items="${produits}">
                <option value="${produit.id}">${produit.nom}</option>
            </c:forEach>
        </select>

        <!-- Menu déroulant des mois -->
        <label for="moisId">Mois :</label>
        <select name="moisId" id="moisId" required>
            <c:forEach var="mois" items="${mois}">
                <option value="${mois.id}">${mois.moisNom}</option>
            </c:forEach>
        </select>

        <!-- Bouton pour soumettre -->
        <button type="submit">Valider</button>
    </form>
     <a href="AfficherSelectionServlet">Produits du mois</a>
</body>
</html>
