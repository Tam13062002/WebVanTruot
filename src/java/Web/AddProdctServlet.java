/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Dao.Database;
import Model.Category;
import Model.Product;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author 20t10
 */
public class AddProdctServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategory = Database.getCategorydao().findAll();
        request.setAttribute("listCategory", listCategory);

        request.setAttribute("title", "ADD PRODUCT");

        request.getRequestDispatcher("./views/addProduct.jsp").include(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("add")) {
          
            String name = request.getParameter("name");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String categoryIdStr = request.getParameter("category");
            String fileName = request.getParameter("fileName");
          
            double price = Double.parseDouble(priceStr);
            int quantity = Integer.parseInt(quantityStr);
            int categoryId = Integer.parseInt(categoryIdStr);

            Database.getProductdao().insertProduct(name, fileName, price, quantity, categoryId, Boolean.TRUE);

         
            response.sendRedirect("product");
        }
    }

}


