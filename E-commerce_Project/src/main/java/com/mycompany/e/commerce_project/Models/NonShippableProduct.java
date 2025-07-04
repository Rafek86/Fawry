/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_project.Models;

/**
 *
 * @author TheGenius
 */
public class NonShippableProduct extends Product {
    
    public NonShippableProduct(String name, double price, int quantity) {
        super(name, price, quantity);
    }
    
    @Override
    public boolean requiresShipping() {
        return false;
    }
}
