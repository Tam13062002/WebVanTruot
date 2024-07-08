/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Impl.CategoryImpl;
import Impl.OrderDetailImpl;
import Impl.OrderImpl;
import Impl.OrderStatusImpl;
import Impl.PaymentImpl;
import Impl.ProductImpl;
import Impl.UserCartImpl;
import Impl.UserImpl;
/**
 *
 * @author 20t10
 */
public class Database {
     public static Categorydao getCategorydao(){
        return new CategoryImpl();
    }
     public static ProductDao getProductdao(){
        return new ProductImpl();
    }
      public static Userdao getUserdao(){
        return new UserImpl();
    }
      public static OrderDao getOrderdao(){
        return new OrderImpl();
    }
       public static PaymentDao getPaymentdao(){
        return new PaymentImpl();
    }
         public static  OrderStatusDao getOrderStatusDao(){
        return new OrderStatusImpl();
    }
         public static OrderDetailDao getOrderDetail(){
        return new OrderDetailImpl();
    }
         public static UserCartDao getUserCartdao(){
        return new UserCartImpl();
    }
}
