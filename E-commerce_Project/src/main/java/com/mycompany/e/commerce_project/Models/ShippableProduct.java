/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_project.Models;

import com.mycompany.e.commerce_project.Interfaces.IShipped;

/**
 *
 * @author TheGenius
 */
public class ShippableProduct extends Product {
    private double weight;
    
    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }
    
    @Override
    public boolean requiresShipping() {
        return true;
    }
    
    public double getWeight() {
        return weight;
    }
}
