package com.mycompany.e.commerce_project.Services;

import com.mycompany.e.commerce_project.Interfaces.IShipped;
import java.util.List;


public class ShippingService {
    private static final double shippingRate = 15.0;
    
    public static class ShippableItem implements IShipped {
        private String name;
        private double weight;
        private int quantity;
        
        public ShippableItem(String name, double weight, int quantity) {
            this.name = name;
            this.weight = weight;
            this.quantity = quantity;
        }
        
        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public double getWeight() {
            return weight;
        }
        
        public int getQuantity() {
            return quantity;
        }
    }
    
    public void processShipment(List<IShipped> items) {
        if (items.isEmpty()) {
            return;
        }
        
        System.out.println("** Shipment notice **");
        double totalWeight = 0.0;
        
        for (IShipped item : items) {
            if (item instanceof ShippableItem) {
                ShippableItem shippableItem = (ShippableItem) item;
                double itemWeight = shippableItem.getWeight() * shippableItem.getQuantity();
                totalWeight += itemWeight;
                System.out.println(shippableItem.getQuantity() + "x " + 
                                 shippableItem.getName() + " " + 
                                 (int)(itemWeight * 1000) + "g");
            }
        }
        
        System.out.println("Total package weight " + totalWeight + "kg");
    }
    
    public double calculateShippingFee(List<IShipped> items) {
        double totalWeight = 0.0;
        for (IShipped item : items) {
            if (item instanceof ShippableItem) {
                ShippableItem shippableItem = (ShippableItem) item;
                totalWeight += shippableItem.getWeight() * shippableItem.getQuantity();
            }
        }
        return totalWeight * shippingRate;
    }
}