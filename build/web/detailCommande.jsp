<%-- 
    Document   : detailCommande
    Created on : 18 janv. 2025, 18:14:37
    Author     : Romeo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
<main id="main" class="main"> 
    <h1>Liste des Détails des Commandes</h1>
   <table>
    <thead>
        <tr>
            <th>ID Commande</th>
            <th>Date Commande</th>
            <th>Statut</th>
            <th>Nom Client</th>
            <th>Produit</th>
            <th>Quantité</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="detailCommande" items="${detailCommandes}">
            <tr>
                <td>${detailCommande.idCommande}</td>
                <td>${detailCommande.dateCommande}</td>
                <td>${detailCommande.statut}</td>
                <td>${detailCommande.nomClient}</td>
                <td>${detailCommande.produit}</td>
                <td>${detailCommande.quantite}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

    <c:if test="${empty detailCommandes}">
    <p>Aucune commande à afficher.</p>
</c:if>
</main>
<%@ include file="./template/footer.jsp" %>