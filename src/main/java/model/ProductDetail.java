/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * @author pqhuy
 */
public class ProductDetail {
    private int id;
    private String size;
    private String color;
    private int quantity;

    public ProductDetail() {

    }

    public ProductDetail(String size, String color, int quantity) {
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public ProductDetail(int id, String size, String color, int quantity) {
        this.id = id;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
