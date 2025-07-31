<%@ page import="java.util.List" %>
<%@ page import="model.Ingredient" %>
<%@ page import="model.Unite" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">
    


<body>
    <h1>Gestion des Ingrédients</h1>

    <!-- Affichage des messages d'erreur -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">
            ${errorMessage}
        </div>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prix Unitaire</th>
                <th>Seuil Minimum</th>
                <th>Unité</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="ingredient" items="${ingredients}">
            <tr>
                <td>${ingredient.idIngredient}</td>
                <td>${ingredient.nom}</td>
                <td>${ingredient.prixUnitaire}</td>
                <td>${ingredient.seuilMinimum}</td>
                <td>${ingredient.unite.id}</td>  <!-- Affichage de l'unité associée -->
                <td>
                    <!-- Formulaire de suppression -->
                    <form action="IngredientServlet" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${ingredient.idIngredient}">
                        <button type="submit">Supprimer</button>
                    </form>

                    <!-- Formulaire de mise à jour -->
                    <button type="button" class="btn-modifier">Modifier</button>

        <!-- Formulaire de modification caché par défaut -->
        <div class="form-container">
            <form action="IngredientServlet" method="post">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="id" value="${ingredient.idIngredient}">
                <input type="text" name="nom" value="${ingredient.nom}" required>
                <input type="number" step="0.01" name="prix" value="${ingredient.prixUnitaire}" required>
                <input type="number" step="0.01" name="seuil" value="${ingredient.seuilMinimum}" required>
                <!-- Liste déroulante pour les unités -->
                <label for="uniteId">Unité</label>
<select name="uniteId" required>
    <c:forEach var="unite" items="${unites}">
        <option value="${unite.id}" 
                <c:if test="${ingredient.unite.id == unite.id}">selected</c:if>>
            ${unite.nom}
        </option>
    </c:forEach>
</select>
                <button type="submit">Mettre à jour</button>
            </form>
        </div>
            </tr>
        </c:forEach>

        <c:if test="${empty ingredients}">
            <tr>
                <td colspan="6">Aucun ingrédient trouvé.</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!-- Formulaire d'ajout d'un ingrédient -->
    <h3>Ajouter un nouvel ingrédient</h3>
    <form action="IngredientServlet" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="nom" placeholder="Nom" required>
        <input type="number" step="0.01" name="prix" placeholder="Prix Unitaire" required>
        <input type="number" step="0.01" name="seuil" placeholder="Seuil Minimum" required> <!-- Nouveau champ -->
        
        <!-- Liste déroulante pour les unités -->
        <select name="uniteId" required>
            <c:forEach var="unite" items="${unites}">
                <option value="${unite.id}">${unite.nom}</option>
            </c:forEach>
        </select>

        <button type="submit">Ajouter</button>
    </form>

    <!-- Boutons pour naviguer -->
    <button onclick="window.location.href='adminDashboard.jsp'">Retour au Tableau Admin</button>
    <button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
    
    <script>
        // Cette fonction s'exécute lorsque le bouton "Modifier" est cliqué
        $(document).ready(function() {
            $('.btn-modifier').click(function() {
                var formContainer = $(this).closest('tr').find('.form-container'); // Sélectionner le formulaire associé
                formContainer.toggleClass('show-form'); // Toggle pour afficher ou masquer
            });
        });
    </script>
</main>
<%@ include file="./template/footer.jsp" %>