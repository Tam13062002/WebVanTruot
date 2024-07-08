/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.UserCart;
import java.util.List;

/**
 *
 * @author 20t10
 */
public interface UserCartDao {
     public void insertUserCart(int userID, int productID,String productName,String image,Double price,  int quantity);
    public   List<UserCart> findAll(int userID);
    public void deleteUserCart(int userID,int productID);
      public void deleteUserCart(int userID);
     public void updateUserCart(int userID,int productID,int quantity);
}
