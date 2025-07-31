<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Produit" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
<main id="main" class="main">

    <h1>Insertion des Commandes</h1>
    <form method="post" action="commandes">
        <input type="hidden" name="action" value="insert">
        
        <label for="nomProduit">Produit :</label>
        <select name="nomProduit" id="nomProduit" required>
            <c:forEach var="produit" items="${produits}">
                <option value="${produit}">${produit}</option>
            </c:forEach>
        </select>

        <label for="quantite">Quantité :</label>
        <input type="number" name="quantite" id="quantite" required>

        <label for="nomClient">Client :</label>
        <select name="nomClient" id="nomClient" required>
            <c:forEach var="client" items="${clients}">
                <option value="${client}">${client}</option>
            </c:forEach>
        </select>

        <label for="statut">Statut :</label>
        <input type="text" name="statut" id="statut" required>

        <label for="dateCommande">Date de commande :</label>
        <input type="datetime-local" name="dateCommande" id="dateCommande" required>

        <button type="submit">Ajouter la commande</button>
    </form>

    <h1>Recherche des Commandes</h1>
    <form method="post" action="commandes">
        <input type="hidden" name="action" value="search">

        <label for="produit">Produit :</label>
        <input type="text" name="produit" id="produit">

        <label for="client">Client :</label>
        <input type="text" name="client" id="client">

       

        <label for="dateDebut">Date début :</label>
        <input type="datetime-local" name="dateDebut" id="dateDebut">

        <label for="dateFin">Date fin :</label>
        <input type="datetime-local" name="dateFin" id="dateFin">

        <button type="submit">Rechercher</button>
    </form>

    <h1>Liste des Commandes</h1>
    <!-- Vérification s'il y a des commandes -->
    <c:if test="${not empty commandes}">
        <table>
            <thead>
                <tr>
                    <th>ID Commande</th>
                    <th>Nom Produit</th>
                    <th>Quantité</th>
                    <th>Nom Client</th>
                 
                    <th>Date Commande</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="commande" items="${commandes}">
                    <tr>
                        <td>${commande.commandeId}</td>
                        <td>${commande.nomProduit}</td>
                        <td>${commande.quantite}</td>
                        <td>${commande.nomClient}</td>
                        <td>${commande.dateCommande}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Message si aucune commande n'est trouvée -->
    <c:if test="${empty commandes}">
        <p>Aucune commande trouvée.</p>
    </c:if>
         <a href="commandes">ok</a>

</main>
<%@ include file="./template/footer.jsp" %>
