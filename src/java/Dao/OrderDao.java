/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;


import Model.Order;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author 20t10
 */
public interface OrderDao {
    public void insertOrder(int customerID,Date orderTime,String payment, Double total,int status);
public int getLatestOrderID(int customerID);
public List<Order> findAll();
   public void updateOrderStatus(int orderId, int newStatus) ;
}
