/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Dao.Database;
import Impl.OrderImpl;
import Model.Order;
import Model.OrderStatus;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author 20t10
 */
public class OrderServlet extends HttpServlet {

  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Order> listOrder = Database.getOrderdao().findAll();
    request.setAttribute("listOrder", listOrder);
    List<OrderStatus> listOrderStatus = Database.getOrderStatusDao().findAll();
    request.setAttribute("listOrderStatus", listOrderStatus);

    // Truyền thuộc tính "id" của Order vào JSP
   

    request.setAttribute("title", "Order");

    request.getRequestDispatcher("./views/order.jsp").include(request, response);
     
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
     String orderIdStr = request.getParameter("orderId");
    String newStatusStr = request.getParameter("newStatus");

    if (orderIdStr != null && newStatusStr != null) {
        int orderId = Integer.parseInt(orderIdStr);
        int newStatus = Integer.parseInt(newStatusStr);

        Database.getOrderdao().updateOrderStatus(orderId, newStatus);

            // Trả về kết quả thành công
            response.getWriter().write("success");
        }
    }
   


}
