<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">
    <h1>Ajouter une Nouvelle Recommandation</h1>

    <form action="InsertionRecommandationServlet" method="post">
        <!-- Champ pour la date de début -->
        <label for="dateDebut">Date de Début :</label>
        <input type="date" name="dateDebut" id="dateDebut" required>
        <br><br>

        <!-- Champ pour la date de fin -->
        <label for="dateFin">Date de Fin :</label>
        <input type="date" name="dateFin" id="dateFin" required>
        <br><br>

        <!-- Liste déroulante des produits -->
        <label for="idProduit">Produit :</label>
        <select name="idProduit" id="idProduit" required>
            <option value="" disabled selected>-- Sélectionnez un produit --</option>
            <c:forEach var="produit" items="${produits}">
                <option value="${produit.id}">${produit.nom}</option>
            </c:forEach>
        </select>
        <br><br>

        <!-- Bouton de soumission -->
        <button type="submit">Ajouter</button>
    </form>
     <a href="AfficherRecommandationsServlet">Produits du mois</a>
</body>
</html>
</main>
<%@ include file="./template/footer.jsp" %>
