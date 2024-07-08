/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Web;

import Dao.Database;
import Model.Email;
import Model.Product;
import Model.User;
import Model.UserCart;
import com.mysql.cj.protocol.Message;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author 20t10
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Login Page");

        request.getRequestDispatcher("./views/login.jsp").include(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String emailphone = request.getParameter("emailPhone");
        String password = request.getParameter("password");
        User us = Database.getUserdao().findUser(emailphone, password);

        if (us == null) {
            request.getSession().setAttribute("erorr_login", "Your information is incorect!");
            response.sendRedirect("login");
        } else {
            if (us.getRole().equals("admin")) {
                request.getSession().setAttribute("user", us);
                request.getSession().removeAttribute("erorr_login");
                response.sendRedirect("admin");
            } else {
                
                request.getSession().setAttribute("user", us);
                request.getSession().removeAttribute("erorr_login");
                response.sendRedirect("home");
            }
        }
    }



}
