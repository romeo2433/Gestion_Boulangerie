<!DOCTYPE html>
<html>
<head>
    <title>D�tails de Fabrication</title>
</head>
<body>
    <h1>D�tails de Fabrication</h1>
    <form action="FabricationServlet" method="post">
        <input type="hidden" name="idProduit" value="<%= request.getAttribute("idProduit") %>">
        <table border="1">
            <tr>
                <th>Ingr�dient</th>
                <th>Quantit� N�cessaire</th>
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
        <label for="quantite">Quantit� � Fabriquer :</label>
        <input type="number" name="quantite" id="quantite" required>
        <button type="submit">Fabriquer</button>
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
