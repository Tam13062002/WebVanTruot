/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Model.Category;
import Model.Product;

import Dao.Database;
import Model.User;
import Model.UserCart;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 20t10
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Category> listCategory = Database.getCategorydao().findAll();
        request.setAttribute("listCategory", listCategory);
        List<Product> listProduct = Database.getProductdao().findAll();
        request.setAttribute("listProduct", listProduct);

        request.setAttribute("id_category", request.getParameter("id_category"));

                    
        addProductToCart(request);
           
        request.setAttribute("title", "Home Page");
        request.getRequestDispatcher("./views/home.jsp").include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }

    private void addProductToCart(HttpServletRequest request) {
        String idProductParam = request.getParameter("id_product");
        User user = (User) request.getSession().getAttribute("user");
      
        if (idProductParam != null && !idProductParam.isEmpty()) {
            try {
                int id_product = Integer.parseInt(idProductParam);
                if (id_product > 0) {
                    List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
                    if (cart == null) {
                        cart = new ArrayList<>();
                    }

                    Product product = Database.getProductdao().findProduct(id_product);

                    if (product != null) {
                        boolean isProductInCart = false;
                        for (Product pro : cart) {
                            if (pro.getId() == id_product) {
                                pro.setQuantity(pro.getQuantity() + 1);
                                isProductInCart = true;
                                Database.getUserCartdao().updateUserCart(user.getId(), pro.getId(), pro.getQuantity());
                                break;
                            }
                        }
                        if (!isProductInCart) {
                            cart.add(product);
                            Database.getUserCartdao().insertUserCart( user.getId(), id_product,product.getName(),product.getImage(),product.getPrice(), product.getQuantity());
                        }

                        request.getSession().setAttribute("cart", cart);
                    }
                }
                
            } catch (NumberFormatException e) {
                // Xử lý nếu không thể chuyển đổi thành số nguyên
                // Đối với ví dụ này, có thể không làm gì hoặc gửi một thông báo lỗi
                // response.sendRedirect("home?error=invalidProductId");
            }
            
        }
    }

}
