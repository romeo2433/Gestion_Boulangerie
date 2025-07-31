package servlet;

import controller.DetailCommandeController;
import model.DetailCommande;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DetailCommandeServlet")
public class DetailCommandeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DetailCommandeController controller = new DetailCommandeController();
        List<DetailCommande> detailCommandes = controller.getAllDetailCommandes();

        // Passer la liste des commandes à la JSP
        request.setAttribute("detailCommandes", detailCommandes);
        request.getRequestDispatcher("detailCommande.jsp").forward(request, response);
    }
    
     
}
