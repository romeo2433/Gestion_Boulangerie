<%@ page import="model.Ingredient" %>
<%@ page import="model.Unite" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="controller.IngredientController" %>
<%@ page import="java.util.List" %>
<%
    // Récupérer l'ID de l'ingrédient depuis l'URL
    String idStr = request.getParameter("id");
    int id = Integer.parseInt(idStr);
    
    // Utilisation du contrôleur pour obtenir l'ingrédient
    IngredientController ingredientController = new IngredientController();
    Ingredient ingredient = ingredientController.getIngredientById(id);
    List<Unite> unites = ingredientController.getAllUnites();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier Ingrédient</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Modifier Ingrédient</h1>

    <!-- Formulaire de modification d'ingrédient -->
    <form action="IngredientServlet" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="<%= ingredient.getIdIngredient() %>">
        
        <label for="nom">Nom</label>
        <input type="text" name="nom" value="<%= ingredient.getNom() %>" required>
        
        <label for="prix">Prix Unitaire</label>
        <input type="number" step="0.01" name="prix" value="<%= ingredient.getPrixUnitaire() %>" required>
        
        <label for="seuil">Seuil Minimum</label>
        <input type="number" step="0.01" name="seuil" value="<%= ingredient.getSeuilMinimum() %>" required>
        
        <label for="uniteId">Unité</label>
        <select name="uniteId" required>
            <c:forEach var="unite" items="${unites}">
                <option value="${unite.id}" <%= ingredient.getUnite().getId() == unite.getId() ? "selected" : "" %>>${unite.nom}</option>
            </c:forEach>
        </select>

        <button type="submit">Mettre à jour</button>
    </form>

    <button onclick="window.location.href='gestionIngredients.jsp'">Retour à la gestion des ingrédients</button>

</body>
</html>
