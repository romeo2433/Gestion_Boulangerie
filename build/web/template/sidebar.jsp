<aside id="sidebar" class="sidebar">

  <style>
    /* Animation de la sidebar */
    .sidebar {
        transition: transform 0.3s ease, opacity 0.3s ease;
        opacity: 0;
        transform: translateX(-100%);
    }

    .sidebar.loaded {
        opacity: 1;
        transform: translateX(0);
    }

    /* Animation pour les liens */
    .sidebar-nav .nav-link {
        transition: transform 0.2s ease, color 0.3s ease;
    }

    .sidebar-nav .nav-link:hover {
        color: #3498db; /* Vous pouvez personnaliser cette couleur */
        transform: translateX(10px);
    }
</style>

<script>
    // Ajouter la classe 'loaded' pour l'animation de la sidebar après le chargement de la page
    window.addEventListener('DOMContentLoaded', () => {
        document.querySelector('.sidebar').classList.add('loaded');
    });
</script>


<script>
    // Ajouter la classe 'loaded' au chargement
    window.addEventListener('DOMContentLoaded', () => {
        document.querySelector('.sidebar').classList.add('loaded');
    });

    // Gérer la réduction ou l'expansion de la sidebar
    document.querySelector('.sidebar-toggle').addEventListener('click', () => {
        const sidebar = document.querySelector('.sidebar');
        sidebar.classList.toggle('collapsed');
    });
</script>

    <ul class="sidebar-nav" id="sidebar-nav">

        <li class="nav-item">
            <a class="nav-link" href="index.html">
                <i class="bi bi-grid"></i>
                <span>Dashboard</span>
            </a>
        </li><!-- End Dashboard Nav -->

        <li class="nav-item">
            <a class="nav-link collapsed" data-bs-target="#components-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-menu-button-wide"></i><span>Components</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" data-bs-target="#forms-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-journal-text"></i><span>Forms</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
        </li>

        <li class="nav-item">
            <a class="nav-link collapsed" data-bs-target="#icons-nav" data-bs-toggle="collapse" href="#">
                <i class="bi bi-gem"></i><span>Icons</span><i class="bi bi-chevron-down ms-auto"></i>
            </a>
        </li>
        <li class="nav-heading">Administration</li>
       

        <li class="nav-item">
            <a class="nav-link collapsed" href="pages-login.html">
                <i class="bi bi-box-arrow-in-right"></i>
                <span>Login</span>
            </a>
        </li><!-- End Login Page Nav -->

        <li class="nav-item">
            <a class="nav-link collapsed" href="pages-error-404.html">
                <i class="bi bi-dash-circle"></i>
                <span>Error 404</span>
            </a>
        </li><!-- End Error 404 Page Nav -->

        <li class="nav-item">
            <a class="nav-link collapsed" href="pages-blank.html">
                <i class="bi bi-file-earmark"></i>
                <span>Blank</span>
            </a>
        </li><!-- End Blank Page Nav -->

         <li class="nav-heading">Pages</li>

        <li class="nav-item">
            <a class="nav-link collapsed" href="FabricationServlet">
                <i class="bi bi-hammer"></i>
                <span>Fabrication</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="ProduitServlet?mode=r">
                <i class="bi bi-box"></i>
                <span>Produits</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="CategorieServlet?mode=r">
                <i class="bi bi-tags"></i>
                <span>Catégories</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="IngredientServlet?mode=r">
                <i class="bi bi-basket"></i>
                <span>Ingrédients</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="commandes">
                <i class="bi bi-cart-check"></i>
                <span>Commandes</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="stockservlet?mode=r">
                <i class="bi bi-boxes"></i>
                <span>Stocks</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="statistiques.jsp">
                <i class="bi bi-bar-chart"></i>
                <span>Statistiques</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="recetteServlet">
                <i class="bi bi-book"></i>
                <span>Recettes</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="VenteServlet">
                <i class="bi bi-shop"></i>
                <span>Vente</span>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link collapsed" href="InsertionRecommandationServlet">
                <i class="bi bi-calendar3"></i>
                <span>Produits du mois</span>
            </a>
        </li>
        
        <li class="nav-item">
    <a class="nav-link collapsed" href="DetailCommandeServlet">
        <i class="bi bi-list-check"></i>
        <span>Détails des Commandes</span>
    </a>
</li>


    </ul>

    <div class="content">
        <h1>Tableau de Bord Administrateur</h1>
        <p>Bienvenue, Administrateur !</p>
        <button onclick="window.location.href='index.jsp'">Retour à l'accueil</button>
    </div>

</aside><!-- End Sidebar-->
