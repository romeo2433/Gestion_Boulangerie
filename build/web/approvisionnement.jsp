<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="controller.IngredientController" %>
<%@ page import="model.Ingredient" %>

<%
    IngredientController ingredientController = new IngredientController();
    List<Ingredient> ingredients = ingredientController.getAllIngredients();
    String message = request.getParameter("message");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Approvisionnement des Ingrédients</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Approvisionnement des Ingrédients</h1>

    <% if (message != null) { %>
        <p style="color: green;"><%= message %></p>
    <% } %>

    <form action="ApprovisionnementServlet" method="post">
        <label for="ingredient">Ingrédient :</label>
        <select name="ingredient_id" id="ingredient">
            <% for (Ingredient ingredient : ingredients) { %>
                <option value="<%= ingredient.getIdIngredient() %>">
                    <%= ingredient.getNom() %>
                </option>
            <% } %>
        </select>

        <label for="quantite">Quantité :</label>
        <input type="number" name="quantite" id="quantite" required>

        <label for="prix">Prix Total :</label>
        <input type="number" name="prix" id="prix" step="0.01" required>

        <button type="submit">Ajouter</button>
    </form>

    <a href="gestionStocks.jsp">Retour à la Gestion de Stock</a>
</body>
</html>
