
<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
 <main id="main" class="main">


<h1>Fabriquer un Produit</h1>

    <!-- Affichage du message de succ�s ou d'erreur -->
    <c:if test="${not empty message}">
        <p style="color: ${success ? 'green' : 'red'};">${message}</p>
    </c:if>
        
     <c:if test="${not empty erreur}">
        <p style="color: ${erreur ? 'red' : 'red'};">${erreur}</p>
    </c:if>

    <!-- Formulaire pour s�lectionner le produit et la quantit� -->
    <form action="FabricationServlet" method="post">
        <div>
            <label for="produitId">Produit � fabriquer :</label>
            <select name="produitId" id="produitId" required>
                <!-- Afficher les options des produits disponibles -->
                <c:forEach var="produit" items="${produits}">
                    <option value="${produit.id}">${produit.nom}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <label for="quantite">Quantit� � fabriquer :</label>
            <input type="number" name="quantite" id="quantite" min="1" required>
        </div>

        <button type="submit">Fabriquer</button>
    </form>

    <!-- Lien vers le tableau de bord de gestion des stocks -->
    <button onclick="window.location.href='stockservlet?mode=r'">Retour � la gestion des stocks</button>
     <button onclick="window.location.href='adminDashboard.jsp'">Retour au tableau admin</button>
 </main>
<%@ include file="./template/footer.jsp" %>