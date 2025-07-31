<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion Administrateur</title>
    <style>
        /* Style global */
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #4c83ff, #1a73e8);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #ffffff;
        }

        /* Conteneur principal */
        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            padding: 30px;
            width: 100%;
            max-width: 400px;
            color: #333333;
            text-align: center;
        }

        /* Titre principal */
        .form-container h1 {
            font-size: 24px;
            margin-bottom: 20px;
            color: #1a73e8;
        }

        /* Labels et champs */
        .form-container label {
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 5px;
            display: block;
            text-align: left;
        }

        .form-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            font-size: 14px;
        }

        /* Boutons */
        .form-container button {
            width: 100%;
            background-color: #1a73e8;
            color: #ffffff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-container button:hover {
            background-color: #145abb;
        }

        /* Lien inscription */
        .form-container p {
            font-size: 14px;
            margin-top: 15px;
            color: #666666;
        }

        .form-container p a {
            color: #1a73e8;
            text-decoration: none;
            font-weight: bold;
        }

        .form-container p a:hover {
            text-decoration: underline;
        }

        /* Bouton retour */
        .form-container .btn-back {
            margin-top: 15px;
            background-color: #cccccc;
            color: #333333;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .form-container .btn-back:hover {
            background-color: #999999;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Connexion Administrateur</h1>
        <form action="AdminServlet" method="post">
            <label for="email">Email :</label>
            <input type="email" name="email" id="email" placeholder="Entrez votre email" required>
            <label for="password">Mot de passe :</label>
            <input type="password" name="password" id="password" placeholder="Entrez votre mot de passe" required>
            <input type="hidden" name="action" value="login">
            <button type="submit">Se connecter</button>
        </form>
        <p>Pas encore de compte ? <a href="inscriptionAdmin.jsp">Inscrivez-vous ici</a></p>
        <button class="btn-back" onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
    </div>
</body>
</html>
