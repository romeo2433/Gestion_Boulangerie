<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion Client</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Connexion Client</h1>
    <form action="UtilisateurServlet" method="post">
        <input type="hidden" name="action" value="loginClient">
        <label for="email">Email :</label>
        <input type="email" name="email" id="email" required>
        <br>
        <label for="password">Mot de Passe :</label>
        <input type="password" name="password" id="password" required>
        <br>
        <button type="submit">Se Connecter</button>
    </form>
    <p>Pas encore de compte ? <a href="inscriptionClient.jsp">S'inscrire</a></p>
       <br>
<button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</body>
</html>
