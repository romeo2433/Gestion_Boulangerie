<%@ page import="java.util.List" %>
<%@ page import="model.Categorie" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
<main id="main" class="main">
    <h1>Gestion des Catégories</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
                if (categories != null) {
                    for (Categorie categorie : categories) {
            %>
                <tr>
                    <td><%= categorie.getId() %></td>
                    <td><%= categorie.getNom() %></td>
                    <td>
                        <form action="CategorieServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= categorie.getId() %>">
                            <button type="submit">Supprimer</button>
                        </form>
                    </td>
                </tr>
            <% 
                    }
                } else {
            %>
                <tr>
                    <td colspan="3">Aucune catégorie trouvée.</td>
                </tr>
            <% 
                }
            %>
        </tbody>
    </table>

    <h2>Ajouter une Catégorie</h2>
    <form action="CategorieServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" required>
        <button type="submit">Ajouter</button>
    </form>
    
    <br><!-- comment -->
        <button onclick="window.location.href='adminDashboard.jsp'">Retour au tableau admin</button>
<button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</main>
<%@ include file="./template/footer.jsp" %>