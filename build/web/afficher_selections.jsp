<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="css/style.css">
    <title>Produits Sélectionnés</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Liste des Produits du Mois</h2>
    <table>
        <thead>
            <tr>
                <th>Produit</th>
                <th>Mois</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="selection" items="${selections}">
                <tr>
                    <td>${selection[0]}</td>
                    <td>${selection[1]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
