/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.UserCartDao;
import Driver.MySqlDriver;
import Model.UserCart;
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
public class UserCartImpl implements UserCartDao {

    Connection con = MySqlDriver.getConnection();

    @Override
    public void insertUserCart(int userID, int productID,String productName,String image,Double price, int quantity) {
        String sql = "INSERT INTO usercart (userID,productID,productName,image,price,  quantity) VALUES (?,?,?,?, ?, ?)";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, userID);
            sttm.setInt(2, productID);
            sttm.setString(3,productName );
            sttm.setString(4,image );
            sttm.setDouble(5, price);
            sttm.setInt(6, quantity);

            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    @Override
    public void deleteUserCart(int userID,int productID) {
       String sql = "DELETE FROM usercart WHERE userID=? and productID=? ";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, userID);
            sttm.setInt(2, productID);
            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUserCart(int userID, int productID,int quantity) {
      String sql = "UPDATE usercart SET quantity=? where userID=? and productID=? ";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, quantity);
            sttm.setInt(2, userID);
            sttm.setInt(3, productID);
            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserCart> findAll(int userID) {
       List<UserCart> listUserCart = new ArrayList<>();
        String sql = "select * from usercart where userID =?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            sttm.setInt(1, userID);
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                 int id = rs.getInt("id");
             
                int productID = rs.getInt("productID");
                String name=rs.getString("productName");
                String image=rs.getString("image");
                Double price=rs.getDouble("price");
                int quantity = rs.getInt("quantity");
               listUserCart.add(new UserCart(id, userID, productID, name, image, price, quantity));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listUserCart;
    }

    @Override
    public void deleteUserCart(int userID) {
         String sql = "DELETE FROM usercart WHERE userID=? ";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, userID);
           
            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
