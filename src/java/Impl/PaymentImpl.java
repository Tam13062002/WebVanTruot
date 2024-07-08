/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.PaymentDao;
import Driver.MySqlDriver;
import Model.Payment;
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
public class PaymentImpl implements PaymentDao {
     Connection con= MySqlDriver.getConnection();
    @Override
    public List<Payment> findAll() {
       List<Payment> listPayment= new ArrayList<>();
       String sql="select * from payments";
        try {
            PreparedStatement sttm= con.prepareStatement(sql);
            
            ResultSet rs = sttm.executeQuery();
            while(rs.next()){
                 String paymentID=rs.getString("payment");
                String name=rs.getString("discription");
                listPayment.add(new Payment(paymentID,name));
            }
            
         } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPayment;
    }

}
