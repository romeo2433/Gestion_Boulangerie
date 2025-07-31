<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="./template/header.jsp" %>
<%@ include file="./template/sidebar.jsp" %>
<main id="main" class="main">
    <h1>Statistiques des ventes par sexe</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Sexe</th>
                <th>Montant Total</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="entry" items="${montantParSexe}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value} </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</main>
<%@ include file="./template/footer.jsp" %>