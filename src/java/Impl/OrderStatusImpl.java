/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.OrderStatusDao;
import Driver.MySqlDriver;
import Model.OrderStatus;
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
public class OrderStatusImpl implements OrderStatusDao {
    Connection con= MySqlDriver.getConnection();
    @Override
    public List<OrderStatus> findAll() {
       List<OrderStatus> listOrderStatus= new ArrayList<>();
       String sql="select * from orderstatus";
        try {
            PreparedStatement sttm= con.prepareStatement(sql);
            
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                 int status=rs.getInt("Status");
                String description=rs.getString("description");
                listOrderStatus.add(new OrderStatus(status, description));
            }
            
         } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderStatus;
    }
}
