/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.OrderStatus;
import java.util.List;

/**
 *
 * @author 20t10
 */
public interface OrderStatusDao {
    public List<OrderStatus> findAll();
}
