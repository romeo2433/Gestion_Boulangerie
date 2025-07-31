<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Ingrédient</title>
    <style>
        /* Style général */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h1 {
            color: #007bff;
            margin-bottom: 20px;
            text-transform: uppercase;
        }

        /* Style du formulaire */
        form {
            background: #fff;
            padding: 20px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        input:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 4px rgba(0, 123, 255, 0.5);
        }

        button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        /* Style du bouton Retour */
        button.secondary {
            background-color: #6c757d;
            margin-top: 10px;
        }

        button.secondary:hover {
            background-color: #5a6268;
        }

        /* Alignement centré */
        .button-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>Ajouter un Ingrédient</h1>
    <form action="IngredientServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label for="nom">Nom :</label>
        <input type="text" name="nom" id="nom" required>
        
        <label for="prix">Prix :</label>
        <input type="number" name="prix" id="prix" step="0.01" required>
        
        <label for="stock">Stock :</label>
        <input type="number" name="stock" id="stock" required>
        
        <button type="submit">Ajouter</button>
    </form>
    <div class="button-container">
        <button class="secondary" onclick="window.location.href='gestionIngredient.jsp'">Retour</button>
    </div>
</body>
</html>
