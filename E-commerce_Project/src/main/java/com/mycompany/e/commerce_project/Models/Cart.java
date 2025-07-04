/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_project.Models;

import com.mycompany.e.commerce_project.Interfaces.IExpired;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TheGenius
 */
public class Cart {
 private List<CartItem> items;
    
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public void add(Product product, int quantity) {
        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Insufficient quantity available for " + product.getName());
        }
        
        if (product instanceof IExpired && ((IExpired)product).isExpired()) {
            throw new IllegalArgumentException("Product " + product.getName() + " is expired");
        }
        
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) {
                    throw new IllegalArgumentException("Insufficient quantity available for " + product.getName());
                }
                items.remove(item);
                items.add(new CartItem(product, newQuantity));
                return;
            }
        }
        
        items.add(new CartItem(product, quantity));
    }
    
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public double getSubtotal() {
        return items.stream()
                   .mapToDouble(CartItem::getTotalPrice)
                   .sum();
    }
    public void clear() {
        items.clear();
    }
}