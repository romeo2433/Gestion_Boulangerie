<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription Administrateur</title>
   
</head>
<body>
    <h1>Inscription Administrateur</h1>
<form action="AdminServlet" method="post">
    <label for="name">Nom :</label>
    <input type="text" name="name" id="name" required>
    <br>
    <label for="email">Email :</label>
    <input type="email" name="email" id="email" required>
    <br>
    <label for="password">Mot de passe :</label>
    <input type="password" name="password" id="password" required>
    <br>
    <input type="hidden" name="action" value="register"> <!-- Ajout du paramètre action -->
    <button type="submit">Créer un compte</button>
</form>
   <br>
<button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
</body>
</html>
