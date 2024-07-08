/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Dao.Database;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author 20t10
 */
public class ProductServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> listProduct = Database.getProductdao().findAll();
        request.setAttribute("listProduct", listProduct);
       request.setAttribute("title", "Product");

        request.getRequestDispatcher("./views/product.jsp").include(request, response);
     
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

        if ("delete".equals(action)) {
            // Lấy productId từ request
            int productId = Integer.parseInt(request.getParameter("productId"));

            try {
                 Database.getProductdao().deleteProduct(productId);
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            

            // Gửi thông báo xóa thành công đến trang product
            request.setAttribute("deleteSuccess", "Xóa sản phẩm thành công");
        }

        // Sau khi xóa, chuyển hướng lại đến trang product để hiển thị danh sách cập nhật
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
