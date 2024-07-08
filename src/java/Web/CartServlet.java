/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Model.Product;
import Dao.Database;
import Dao.OrderDao;
import Model.Payment;
import Model.User;
import Model.UserCart;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Payment> listPayment = Database.getPaymentdao().findAll();
        request.setAttribute("listPayment", listPayment);
        User user = (User) request.getSession().getAttribute("user");
        if (request.getParameter("clear") != null ) {
            request.getSession().setAttribute("cart", new ArrayList<>());
            Database.getUserCartdao().deleteUserCart(user.getId());
        }
         
        request.setAttribute("title", "Cart Detail");

        request.getRequestDispatcher("./views/cart.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("checkout".equals(action)) {
            Checkout(request, response);
        } else {
            updateDelete(request, response);
        }
    }

    private void Checkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int customerID = user.getId();

        Date orderTime = new Date(System.currentTimeMillis());
        String payment = request.getParameter("paymentMethod");
        // double total = Double.parseDouble(request.getAttribute("subtotal").toString());
        double total = Double.parseDouble(request.getParameter("subtotal"));

        // Insert the order into the database
        Database.getOrderdao().insertOrder(customerID, orderTime, payment, total, 1);
        int orderID = Database.getOrderdao().getLatestOrderID(customerID);
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        for (Product product : cart) {
             
            int quantity = product.getQuantity();
            Database.getOrderDetail().insertOrderDetail(orderID, product.getId(), quantity, product.getPrice());
            Database.getProductdao().updateSoLuong(product.getId(), quantity);
            Database.getUserCartdao().deleteUserCart(user.getId());
        }
        request.getSession().setAttribute("cart", new ArrayList<>());

        // Redirect to a confirmation page or another page as needed
        response.sendRedirect("home");

    }

    void updateDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id_product"));
        switch (action) {
            case "delete":
                doDelete(request, id);
                break;
            case "update":
                doUpdate(request, id);
                break;

        }
        response.sendRedirect("cart");
    }

    void doUpdate(HttpServletRequest request, int id_product) {
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        int quantity = Integer.parseInt(request.getParameter("quantity_" + id_product));
         User user = (User) request.getSession().getAttribute("user");
        
        for (Product product : cart) {
            if (product.getId() == id_product && quantity > 0) {
                product.setQuantity(quantity);
                Database.getUserCartdao().updateUserCart(user.getId(), product.getId(), product.getQuantity());
                break;
            }
        }
        request.getSession().setAttribute("cart", cart);
    }

    void doDelete(HttpServletRequest request, int id_product) {
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        
        for (Product product : cart) {
            if (product.getId() == id_product) {
                cart.remove(product);
                Database.getUserCartdao().deleteUserCart(user.getId(), id_product);
                break;
            }
        }
        request.getSession().setAttribute("cart", cart);
    }

}
