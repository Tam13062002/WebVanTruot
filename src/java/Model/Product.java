/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 20t10
 */
public class Product {

    int id;
    int id_category;
    String name;
    String image;
    double price;
    int quantity;
    boolean status;

    public Product() {
    }

    public Product(int id, int id_category, String name, String image, double price, int quantity, boolean status) {
        this.id = id;
        this.id_category = id_category;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Product(ResultSet rs) throws SQLException {
        this.id = rs.getInt("productID");
        this.name = rs.getString("productName");
        this.image = rs.getString("photo");
        this.price = rs.getDouble("price");
        this.quantity =1;

    }
    public Product(ResultSet rs ,int id) throws SQLException {
        this.id = rs.getInt("productID");
        this.name = rs.getString("productName");
        this.image = rs.getString("photo");
        this.price = rs.getDouble("price");
        this.id_category = rs.getInt("categoryID");
        this.quantity =rs.getInt("quantity");

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   

}
