/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import Dao.Database;
import Driver.MySqlDriver;
import Model.Product;
import Dao.ProductDao;
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
public class ProductImpl implements ProductDao {

    Connection con = MySqlDriver.getConnection();

    @Override
    public List<Product> findAll() {
        List<Product> listProduct = new ArrayList<>();
        String sql = "select * from products";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);

            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("productID");
                int id_category = rs.getInt("categoryID");
                String name = rs.getString("productName");
                String image = rs.getString("photo");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                listProduct.add(new Product(id, id_category, name, image, price, quantity, status));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    @Override
    public Product findProduct(int id_product) {
        String sql = "select *from products where productID=" + id_product;
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return new Product(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insertProduct(String productName, String photo, Double price, int quantity, int categoryID, Boolean status) {
        String sql = "INSERT INTO products (productName, photo, price, quantity, categoryID, status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);

            sttm.setString(1, productName);
            sttm.setString(2, photo);
            sttm.setDouble(3, price);
            sttm.setInt(4, quantity);
            sttm.setInt(5, categoryID);
            sttm.setBoolean(6, status);

            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        String sql = "UPDATE products SET  productName=?, photo=?, price=?, quantity=?,categoryID=?, status=? WHERE productID=?";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);

            sttm.setString(1, product.getName());
            sttm.setString(2, product.getImage());
            sttm.setDouble(3, product.getPrice());
            sttm.setInt(4, product.getQuantity());
            sttm.setInt(5, product.getId_category());
            sttm.setBoolean(6, product.isStatus());
            sttm.setInt(7, product.getId());

            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id_product) {
        String sql = "DELETE FROM products WHERE productID=?";
        try (PreparedStatement sttm = con.prepareStatement(sql)) {
            sttm.setInt(1, id_product);
            sttm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findPro(int id_product) {
        String sql = "select *from products where productID=" + id_product;
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            ResultSet rs = sttm.executeQuery();
            if (rs.next()) {
                return new Product(rs, id_product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateSoLuong(int id, int soluong) {
        Product pro = findPro(id);
        if (pro.getQuantity() > soluong) {
            String sql = "update products set quantity=? where productID=?";
            try {
                PreparedStatement sttm = con.prepareStatement(sql);
                sttm.setInt(1, pro.getQuantity() - soluong);
                sttm.setInt(2, id);
                sttm.executeUpdate();
            } catch (Exception e) {
            }
        } else  {
            String sql = "UPDATE products set quantity=?,status=? WHERE productID=?";
            try {
                PreparedStatement sttm = con.prepareStatement(sql);
                sttm.setInt(1, 0);
                sttm.setBoolean(2, false);
                sttm.setInt(3, id);
                sttm.executeUpdate();
            } catch (Exception e) {
            }

        }
    }
     @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE productName LIKE ? ";
        try {
            PreparedStatement sttm = con.prepareStatement(sql);
            
            // Thêm tham số vào câu truy vấn
            sttm.setString(1, "%" + keyword + "%");
           
           
            
            ResultSet rs = sttm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("productID");
                int id_category = rs.getInt("categoryID");
                String name = rs.getString("productName");
                String image = rs.getString("photo");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                boolean status = rs.getBoolean("status");
                productList.add(new Product(id, id_category, name, image, price, quantity, status));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

}
