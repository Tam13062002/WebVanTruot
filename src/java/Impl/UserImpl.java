/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;


import Driver.MySqlDriver;
import Model.User;
import Dao.Userdao;
import utils.API;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 20t10
 */
public class UserImpl implements Userdao{
    Connection cn=MySqlDriver.getConnection();
    @Override
    public User findUser(String username, String password){
        String sql;
        if(username.contains("@"))
            sql="Select * from users where email='"+username+"' and password='"+API.getMd5(password)+"'";
        else
            sql="Select * from users where phone='"+username+"' and password='"+API.getMd5(password)+"'";
           
        try {
           PreparedStatement sttm = cn.prepareStatement(sql);
             ResultSet rs= sttm.executeQuery();
             if(rs.next()) return new User(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return  null;
    }

    @Override
    public User findUser(String emailphone) {
      
    
    
        String sql;
        if(emailphone.contains("@"))
            sql="Select * from users where email='"+emailphone +"'" ;
        else
            sql="Select * from users where phone='"+emailphone +"'" ;
           
        try {
           PreparedStatement sttm = cn.prepareStatement(sql);
             ResultSet rs= sttm.executeQuery();
             if(rs.next()) return new User(rs);
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return  null;
    }

    @Override
    public void insertUser(String name, String email, String phone, String password) {
     String sql= "insert into users(name,email,phone,password,role) values('"+name+"','"+email+"','"+phone+"','"+password+"',   '')";
        try {
            PreparedStatement sttm=cn.prepareStatement(sql);
            sttm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
