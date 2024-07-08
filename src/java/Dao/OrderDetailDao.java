/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.OrderDetail;
import java.util.List;

/**
 *
 * @author 20t10
 */
public interface OrderDetailDao {
     public void insertOrderDetail(int orderID,int productID,int quantity, Double price);
     public List<OrderDetail> find(int orderID);
}
