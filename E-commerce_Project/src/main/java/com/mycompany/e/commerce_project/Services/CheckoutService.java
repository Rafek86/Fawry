/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.e.commerce_project.Services;

import com.mycompany.e.commerce_project.Interfaces.IExpired;
import com.mycompany.e.commerce_project.Interfaces.IShipped;
import com.mycompany.e.commerce_project.Models.Cart;
import com.mycompany.e.commerce_project.Models.CartItem;
import com.mycompany.e.commerce_project.Models.Customer;
import com.mycompany.e.commerce_project.Models.ExpirableProduct;
import com.mycompany.e.commerce_project.Models.Product;
import com.mycompany.e.commerce_project.Models.ShippableProduct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TheGenius
 */
public class CheckoutService {
    private ShippingService shippingService;
    
    public CheckoutService() {
        this.shippingService = new ShippingService();
    }
    
    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalArgumentException("Product " + product.getName() + " is out of stock");
            }
            if (product instanceof IExpired && ((IExpired) product).isExpired()) {
                throw new IllegalArgumentException("Product " + product.getName() + " is expired");
            }
        }
        double subtotal = cart.getSubtotal();
        
        List<IShipped> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            if (product.requiresShipping()) {
                double weight = 0.0;
                if (product instanceof ExpirableProduct) {
                    weight = ((ExpirableProduct) product).getWeight();
                } else if (product instanceof ShippableProduct) {
                    weight = ((ShippableProduct) product).getWeight();
                }
                shippableItems.add(new ShippingService.ShippableItem(
                    product.getName(), weight, item.getQuantity()));
            }
        }
        
        double shippingFees = shippingService.calculateShippingFee(shippableItems);
        double totalAmount = subtotal + shippingFees;
        
        if (!customer.canAfford(totalAmount)) {
            throw new IllegalArgumentException("Customer's balance is insufficient");
        }
        
        if (!shippableItems.isEmpty()) {
            shippingService.processShipment(shippableItems);
        }
        
        customer.deductAmount(totalAmount);
      
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }
        printReceipt(cart, subtotal, shippingFees, totalAmount);
        
        cart.clear();
    }
    
    private void printReceipt(Cart cart, double subtotal, double shippingFees, 
                             double totalAmount) {
        System.out.println("** Checkout receipt **");
        
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + 
                             item.getProduct().getName() + " " + 
                             (int)item.getTotalPrice());
        }
        
        System.out.println("----------------------");
        System.out.println("Subtotal " + (int)subtotal);
        System.out.println("Shipping " + (int)shippingFees);
        System.out.println("Amount " + (int)totalAmount);
    }
}
