/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_project.Models;

import com.mycompany.e.commerce_project.Interfaces.IExpired;
import java.time.LocalDate;

public class ExpirableProduct extends Product implements IExpired {
    private LocalDate expirationDate;
    private boolean shippingRequired;
    private double weight;
    
    public ExpirableProduct(String name, double price, int quantity, 
                          LocalDate expirationDate, boolean shippingRequired, double weight) {
        super(name, price, quantity);
        this.expirationDate = expirationDate;
        this.shippingRequired = shippingRequired;
        this.weight = weight;
    }
    
    @Override
    public LocalDate getExpireDate() {
        return expirationDate;
    }
    
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
    
    @Override
    public boolean requiresShipping() {
        return shippingRequired;
    }
    
    public double getWeight() {
        return weight;
    }
}