/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.OrderDao;
import Driver.MySqlDriver;
import Model.Order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20t10
 */
public class OrderImpl implements OrderDao {
      Connection cn=MySqlDriver.getConnection();
    @Override
   public void insertOrder(int customerID, Date orderTime, String payment, Double total, int status) {
        String sql = "INSERT INTO orders (customerID, orderTime, payment, total, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement sttm = cn.prepareStatement(sql);
            sttm.setInt(1, customerID);
            sttm.setDate(2, orderTime);
            sttm.setString(3, payment);
            sttm.setDouble(4, total);
            sttm.setInt(5, status);
            sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getLatestOrderID(int customerID) {
     
        ResultSet resultSet = null;

        int orderID = -1; // Giá trị mặc định nếu không tìm thấy orderID

        try {
            
            String sql = "SELECT MAX(orderID) AS latestOrderID FROM orders WHERE customerID = ?";
            PreparedStatement sttm = cn.prepareStatement(sql);
            sttm.setInt(1, customerID);
            resultSet = sttm.executeQuery();

            if (resultSet.next()) {
                orderID = resultSet.getInt("latestOrderID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            // ... (đóng resultSet, preparedStatement, connection)
        }

        return orderID;
    }

    @Override
    public List<Order> findAll() {
         List<Order> listOrder = new ArrayList<>();
        String sql = "select * from orders";
        try {
            PreparedStatement sttm = cn.prepareStatement(sql);

            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderID");
                int customerID = rs.getInt("customerID");
   
                Date orderTime = rs.getDate("orderTime");
                String payment = rs.getString("payment");
                Double total=rs.getDouble("total");
                int status = rs.getInt("status");
               listOrder.add(new Order(id, customerID, orderTime, payment, total, status));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrder;
    }
     @Override
    public void updateOrderStatus(int orderId, int newStatus) {
    String sql = "UPDATE orders SET status = ? WHERE orderID = ?";
    try {
        PreparedStatement sttm = cn.prepareStatement(sql);
        sttm.setInt(1, newStatus);
        sttm.setInt(2, orderId);
        sttm.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
