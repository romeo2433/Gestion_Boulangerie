<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">
    
    <h2>Stock des Produits</h2>

    <c:if test="${not empty stockProduits}">
        <table border="1">
            <thead>
                <tr>
                    <th>ID Produit</th>
                    <th>Nom</th>
                    <th>Quantité Disponible</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stockProduit" items="${stockProduits}">
                    <tr>
                        <td>${stockProduit.produitId}</td>
                        <td>${stockProduit.nom}</td>
                        <td>${stockProduit.quantiteDisponible}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty stockProduits}">
        <p>Aucun produit en stock.</p>
    </c:if>
        <hr>
         <!-- Affichage des ingrédients en stock -->
    <c:if test="${not empty messageIngredients}">
        <p>${messageIngredients}</p>
    </c:if>

  <c:if test="${not empty stockIngredients}">
    <h2>Ingrédients en Stock</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Nom de l'Ingrédient</th>
                <th>Quantité Disponible</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ingredient" items="${stockIngredients}">
                <tr>
                    <td>${ingredient.nom}</td>
                    <td>${ingredient.quantiteDisponible}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty stockIngredients}">
    <p>Aucun ingrédient en stock.</p>
</c:if>

   <button onclick="window.location.href='adminDashboard.jsp'">Retour au tableau admin</button>
</main>
<%@ include file="./template/footer.jsp" %>
