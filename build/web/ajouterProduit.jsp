<%@ page import="model.Categorie" %>
<%@ page import="java.util.List" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">
    <h1>Ajouter un Produit</h1>
    <form action="ProduitServlet" method="post">
        <input type="hidden" name="action" value="add">

       <label for="nom">Nom :</label>
<input type="text" id="nom" name="nom" required><br>
        <label for="description">Description :</label>
<textarea id="description" name="description" required></textarea><br>
        <label for="categorieId">Catégorie :</label>
        <select id="categorieId" name="categorieId" required>
           <% 
    List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
    if (categories == null) {
        out.println("Erreur : Les catégories n'ont pas été chargées.");
    } else if (categories.isEmpty()) {
        out.println("Aucune catégorie disponible.");
    } else {
        for (Categorie categorie : categories) {
%>
            <option value="<%= categorie.getId() %>"><%= categorie.getNom() %></option>
<% 
        }
    }
%>

        </select><br><br>

        <label for="prix">Prix Unitaire :</label>
        <input type="number" id="prix" name="prix" step="0.01" required><br><br>

     

        <button type="submit">Ajouter</button>
    </form>
 </main>
<%@ include file="./template/footer.jsp" %>