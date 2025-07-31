
<%@ page import="java.util.List" %>
<%@ page import="model.Recommandation" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">
    <h1>Liste des Recommandations</h1>

    <!-- Table des recommandations -->
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>ID Recommandation</th>
                <th>Date Début</th>
                <th>Date Fin</th>
                <th>Produit</th>
            </tr>
        </thead>
        <tbody>
            <!-- Vérifie s'il y a des recommandations -->
            <c:if test="${not empty recommandations}">
                <c:forEach var="recommandations" items="${recommandations}">
                    <tr>
                        <td>${recommandations.idRecommandation}</td>
                        <td>${recommandations.dateDebut}</td>
                        <td>${recommandations.dateFin}</td>
                        <td>${recommandations.produit.nom}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <!-- Affiche un message si aucune recommandation n'est trouvée -->
            <c:if test="${empty recommandations}">
                <tr>
                    <td colspan="4">Aucune recommandation trouvée.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
</main>
<%@ include file="./template/footer.jsp" %>

