<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier une Catégorie</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Modifier une Catégorie</h1>
    <form action="CategorieServlet" method="post">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="id" value="${categorie.id}">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" value="${categorie.nom}" required>
        <br>
        <button type="submit">Modifier</button>
    </form>
           <br>
   
<button onclick="window.location.href='gestionCategories.jsp'">Retour</button>
</body>
</html>
