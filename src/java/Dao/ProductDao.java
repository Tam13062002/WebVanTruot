/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.Product;
import java.util.List;

/**
 *
 * @author 20t10
 */
public interface ProductDao {

    public List<Product> findAll();

    public Product findProduct(int id_product);
     public void insertProduct(String productName,String photo,Double price,int quantity,int categoryID,Boolean status);
    public void updateProduct(Product product);
    public void deleteProduct(int id_product);
     public Product findPro(int id_product);
     public void updateSoLuong(int id, int soluong);
        public List<Product> searchProducts(String keyword);
}
