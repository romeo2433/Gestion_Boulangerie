<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
<main id="main" class="main">
    <h1>Effectuer une Vente</h1>

    <!-- Formulaire de recherche -->
    <form action="VenteServlet" method="get">
        <label for="sexe">Filtrer par sexe : </label>
        <select name="sexe" id="sexe">
            <option value="">Tous</option>
            <c:forEach var="sexe" items="${sexes}">
                <option value="${sexe}" ${param.sexe == sexe ? 'selected' : ''}>${sexe}</option>
            </c:forEach>
        </select>

        <label for="dateDebut">Date de début : </label>
        <input type="date" id="dateDebut" name="dateDebut" value="${param.dateDebut}">

        <label for="dateFin">Date de fin : </label>
        <input type="date" id="dateFin" name="dateFin" value="${param.dateFin}">

        <button type="submit">Rechercher</button>
    </form>

    <!-- Formulaire de vente -->
    <h1>Insertion les produits</h1>
   <form action="VenteServlet" method="post">
    <label for="produit">Produit : </label>
    <select name="produit_id" id="produit" required>
        <c:forEach var="produit" items="${produits}">
            <option value="${produit.id}">${produit.nom} - ${produit.prix} </option>
        </c:forEach>
    </select>
    <br><br>

    <label for="quantite">Quantité à vendre : </label>
    <input type="number" id="quantite" name="quantite" min="1" required>
    <br><br>

    <label for="vendeur_id">Vendeur : </label>
    <select name="vendeur_id" id="vendeur_id" required>
        <c:forEach var="vendeur" items="${vendeurs}">
            <option value="${vendeur.id}">${vendeur.nom} (${vendeur.sexe})</option>
        </c:forEach>
    </select>
    <br><br>

    <!-- Ajout du champ de date -->
    <label for="date_vente">Date de vente : </label>
    <input type="datetime-local" id="date_vente" name="date_vente" required>
    <br><br>

    <button type="submit">Effectuer la vente</button>
</form>

    <!-- Affichage des messages d'erreur ou de succès -->
    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

    <h2>Liste des Ventes</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Vente</th>
                <th>Produit</th>
                <th>Nom du Vendeur</th>
                <th>Sexe du Vendeur</th>
                <th>Date</th>
                <th>Quantité</th>
                <th>Montant Total</th>
                <th>Commission (5%)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="vente" items="${ventes}">
                <tr>
                    <td>${vente.id}</td>
                    <td>${vente.nomProduit}</td>
                    <td>${vente.nomVendeur}</td>
                    <td>${vente.sexeVendeur}</td>
                    <td>${vente.dateVente}</td>
                    <td>${vente.quantite}</td>
                    <td>${vente.montantTotal} </td>
                    <td>${vente.commission} </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
      <a href="VenteServlet">Ok</a>
      <a href="StatistiqueServlet">Statistique</a>
</main>
<%@ include file="./template/footer.jsp" %>