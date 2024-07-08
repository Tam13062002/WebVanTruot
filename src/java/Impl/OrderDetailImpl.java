/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.OrderDetailDao;
import Driver.MySqlDriver;
import Model.OrderDetail;
import java.sql.Connection;
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
public class OrderDetailImpl implements OrderDetailDao {
 Connection cn=MySqlDriver.getConnection();
   
    @Override
    public void insertOrderDetail(int orderID, int productID, int quantity, Double price) {
       String sql = "INSERT INTO orderdetails ( orderID, productID, quantity, price) VALUES ( ?, ?, ?, ?)";
        try {
            PreparedStatement sttm = cn.prepareStatement(sql);
            sttm.setInt(1, orderID);
             sttm.setInt(2, productID);
           sttm.setInt(3, quantity);
           sttm.setDouble(4, price);
            sttm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
   public List<OrderDetail> find(int orderID) {
       List<OrderDetail> listOrderDetail = new ArrayList<>();
        String sql = "select * from orderdetails where orderID=?";
        try {
             PreparedStatement sttm = cn.prepareStatement(sql);
              sttm.setInt(1, orderID);
           ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("orderDetailID");
                int productID = rs.getInt("productID");
                Double price=rs.getDouble("price");
                int quantity = rs.getInt("quantity");
               listOrderDetail.add(new OrderDetail(id, orderID, productID, quantity, price));
            }
           
        }  catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return listOrderDetail;
    }
    
}
