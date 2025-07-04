
package com.mycompany.e.commerce_project;

import com.mycompany.e.commerce_project.Models.Cart;
import com.mycompany.e.commerce_project.Models.Customer;
import com.mycompany.e.commerce_project.Models.ExpirableProduct;
import com.mycompany.e.commerce_project.Models.NonShippableProduct;
import com.mycompany.e.commerce_project.Models.ShippableProduct;
import com.mycompany.e.commerce_project.Services.CheckoutService;
import java.time.LocalDate;

public class ECommerce_Project {

    public static void main(String[] args) {
        ExpirableProduct cheese = new ExpirableProduct("Cheese", 100.0, 10, 
                                                      LocalDate.now().plusDays(7), true, 0.2);
        ExpirableProduct biscuits = new ExpirableProduct("Biscuits", 150.0, 5, 
                                                        LocalDate.now().plusDays(30), true, 0.7);
        ShippableProduct tv = new ShippableProduct("TV", 5000.0, 3, 15.0);
        NonShippableProduct scratchCard = new NonShippableProduct("Mobile Scratch Card", 50.0, 100);
        
        Customer customer = new Customer("John Doe", 1000.0);
        
        Cart cart = new Cart();
        
        CheckoutService checkoutService = new CheckoutService();
        
        System.out.println("=== TEST CASE 1: Successful Checkout ===");
        try {
            cart.add(cheese, 2);
            cart.add(biscuits, 1);
            cart.add(scratchCard, 1);
            
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== TEST CASE 2: Empty Cart ===");
        try {
            checkoutService.checkout(customer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== TEST CASE 3: Insufficient Balance ===");
        try {
            Customer poorCustomer = new Customer("Poor Customer", 10.0);
            cart.add(tv, 1);
            checkoutService.checkout(poorCustomer, cart);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== TEST CASE 4: Out of Stock ===");
        try {
            cart.clear();
            cart.add(cheese, 20);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== TEST CASE 5: Expired Product ===");
        try {
            cart.clear();
            ExpirableProduct expiredProduct = new ExpirableProduct("Expired Milk", 50.0, 5, 
                                                                  LocalDate.now().minusDays(1), true, 0.5);
            cart.add(expiredProduct, 1);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}