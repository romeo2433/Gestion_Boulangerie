<!DOCTYPE html>
<html>
<head>
    <title>Détails de Fabrication</title>
</head>
<body>
    <h1>Détails de Fabrication</h1>
    <form action="FabricationServlet" method="post">
        <input type="hidden" name="idProduit" value="<%= request.getAttribute("idProduit") %>">
        <table border="1">
            <tr>
                <th>Ingrédient</th>
                <th>Quantité Nécessaire</th>
            </tr>
            <% 
                List<Recette> recette = (List<Recette>) request.getAttribute("recette");
                for (Recette r : recette) {
            %>
            <tr>
                <td><%= r.getIdIngredient() %></td>
                <td><%= r.getQuantite() %></td>
            </tr>
            <% } %>
        </table>
        <label for="quantite">Quantité à Fabriquer :</label>
        <input type="number" name="quantite" id="quantite" required>
        <button type="submit">Fabriquer</button>
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
