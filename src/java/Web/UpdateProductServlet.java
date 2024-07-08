/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Dao.Database;
import Dao.ProductDao;
import Model.Category;
import Model.Product;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 20t10
 */
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategory = Database.getCategorydao().findAll();
  
        request.setAttribute("listCategory", listCategory);

        request.setAttribute("title", "UPDATE PRODUCT");
       
        int productId = Integer.parseInt(request.getParameter("id"));
        Product pro = Database.getProductdao().findPro(productId);
        request.setAttribute("product", pro);

        request.getRequestDispatcher("/inc/_updateProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
         if ("update".equals(action)) {
        // Retrieve form data and update the product
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("name");
        String photo = request.getParameter("uploadPhoto");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categoryId = Integer.parseInt(request.getParameter("category"));

        Product updatedProduct = new Product(productId, categoryId, productName, photo, price, quantity, true);
        Database.getProductdao().updateProduct(updatedProduct);

        // Redirect to a success page or display a success message
        response.sendRedirect("product");
    }
    }

}
