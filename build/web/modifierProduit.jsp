<%@ page import="model.Produit" %>
<%@ page import="model.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">

    <h1>Modifier un Produit</h1>
    <form action="ProduitServlet" method="post">
        <input type="hidden" name="action" value="edit">
        
        <% 
            Produit produit = (Produit) request.getAttribute("produit");  // Cast de l'objet produit
            if (produit != null) {
        %>
        <input type="hidden" name="id" value="<%= produit.getId() %>">

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="<%= produit.getNom() %>" required><br><br>

        <label for="categorieId">Catégorie</label>
        <select id="categorieId" name="categorieId">
    <% 
    List<Categorie> categories = (List<Categorie>) request.getAttribute("categories");
    if (categories != null) {
        for (Categorie categorie : categories) {
            String selected = "";  // Par défaut, aucune catégorie n'est sélectionnée pour l'ajout
            if (produit != null && produit.getCategorieId() == categorie.getId()) {
                selected = "selected"; // Sélectionner la catégorie existante
            }
    %>
        <option value="<%= categorie.getId() %>" <%= selected %>><%= categorie.getNom() %></option>
    <% 
        }
    }
    %>
</select>
<br><br>

        <label for="prix">Prix Unitaire :</label>
        <input type="number" id="prix" name="prix" value="<%= produit.getPrix() %>" step="0.01" required><br><br>

       

        <button type="submit">Mettre à jour</button>
        
        <% } else { %>
            <p>Produit non trouvé. Veuillez revenir à la liste des produits.</p>
            <a href="ProduitServlet?action=list">Voir la liste des produits</a>
        <% } %>
    </form>
</main>
<%@ include file="./template/footer.jsp" %>