<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Administrateur</title>
    <style>
        /* Style global */
        body {
            font-family: 'Roboto', Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f9fc;
            color: #333;
        }

        /* En-tête principal */
        h1 {
            background-color: #34495e;
            color: white;
            text-align: center;
            padding: 20px;
            margin: 0;
            font-size: 28px;
        }

        /* Navigation */
        nav {
            display: flex;
            justify-content: center;
            background-color: #2c3e50;
            padding: 10px 0;
        }

        nav a {
            color: white;
            text-decoration: none;
            font-weight: bold;
            font-size: 16px;
            margin: 0 15px;
            padding: 8px 15px;
            border-radius: 4px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        nav a:hover {
            background-color: #1abc9c;
        }

        /* Contenu */
        .content {
            text-align: center;
            padding: 30px 15px;
        }

        .content p {
            font-size: 20px;
            margin: 20px 0;
            color: #555;
        }

        /* Bouton de retour */
        button {
            padding: 12px 25px;
            font-size: 16px;
            background-color: #1abc9c;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #16a085;
        }

        /* Pied de page */
        footer {
            text-align: center;
            background-color: #34495e;
            color: white;
            padding: 10px 0;
            margin-top: 30px;
            font-size: 14px;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            nav {
                flex-direction: column;
            }

            nav a {
                margin: 10px 0;
            }

            button {
                width: 90%;
                margin: 0 auto;
            }
        }
    </style>
</head>
<body>
    <h1>Tableau de Bord Administrateur</h1>
    <nav>
        <a href="FabricationServlet">Fabrication</a>
        <a href="ProduitServlet?mode=r">Produits</a>
        <a href="CategorieServlet?mode=r">Catégories</a>
        <a href="IngredientServlet?mode=r">Ingrédients</a>
        <a href="commandes">Commandes</a>
        <a href="stockservlet?mode=r">Stocks</a>
        <a href="statistiques.jsp">Statistiques</a>
       <a href="recetteServlet">Recettes</a>
       <a href="VenteServlet">Vente</a>
        <a href="InsertionRecommandationServlet">Produits du mois</a>
        <a href="DetailCommandeServlet">Details produits</a>
       
    </nav>
    <div class="content">
        <p>Bienvenue, Administrateur !</p>
        <button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
    </div>
    <footer>
        &copy; 2025 Gestion Boulangerie - Tous droits réservés
    </footer>
</body>
</html>
