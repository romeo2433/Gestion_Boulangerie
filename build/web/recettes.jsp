<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">
    <h1>Ajouter un produit ou un ingrédient à la recette</h1>

    <!-- Formulaire pour ajouter un produit ou un ingrédient -->
    <form action="recetteServlet" method="post">
        <h2>Sélectionner un produit :</h2>
        <select name="produitId">
            <option value="">Sélectionner un produit</option>
            <c:forEach var="produit" items="${produits}">
                <option value="${produit.id}">${produit.nom}</option>
            </c:forEach>
        </select>

        <h2>Sélectionner un ingrédient :</h2>
        <select name="ingredientId">
            <option value="">Sélectionner un ingrédient</option>
            <c:forEach var="ingredient" items="${ingredients}">
                <option value="${ingredient.idIngredient}">${ingredient.nom}</option>
            </c:forEach>
        </select>

        <h2>Quantité :</h2>
        <input type="number" name="quantite" min="1" required />

        <input type="submit" value="Ajouter à la recette" />
    </form>

    <!-- Affichage des recettes -->
    <c:if test="${not empty recettes}">
        <h2>Liste des recettes actuelles :</h2>
        <table>
            <thead>
                <tr>
                    <th>Produit</th>
                    <th>Ingrédient</th>
                    <th>Quantité Utilisée</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="recette" items="${recettes}">
                    <tr>
                        <!-- Affichage du nom du produit -->
                        <td>
                            <c:choose>
                                <c:when test="${not empty recette.produitId}">
                                    <c:forEach var="produit" items="${produits}">
                                        <c:if test="${produit.id == recette.produitId}">
                                            ${produit.nom}
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <!-- Affichage du nom de l'ingrédient -->
                        <td>
                            <c:choose>
                                <c:when test="${not empty recette.ingredientId}">
                                    <c:forEach var="ingredient" items="${ingredients}">
                                        <c:if test="${ingredient.idIngredient == recette.ingredientId}">
                                            ${ingredient.nom}
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    N/A
                                </c:otherwise>
                            </c:choose>
                        </td>

                        <!-- Affichage de la quantité utilisée -->
                        <td>${recette.quantiteUtilisee}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty recettes}">
        <p>Aucune recette à afficher.</p>
    </c:if>
 <button onclick="window.location.href='adminDashboard.jsp'">Retour au tableau admin</button>
</main>
<%@ include file="./template/footer.jsp" %>
