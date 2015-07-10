package ch7.catalogue;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart<Item> {
    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    
    private List<Item> items;
    private double total;
    private int count;
    
    public ShoppingCart() {
        resetItems();
    }
    
    public final void resetItems() {
        items = new ArrayList<Item>();
        total = 0.0;
        count = 0;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void addTotal(double amount) {
        total += amount;
    }
    
    public String getTotalAsCurrency() {
        return currency.format(total);
    }
    
    public void incrCount() {
        count++;
    }

    public final double getTotal() {
        return total;
    }

    public final void setTotal(double total) {
        this.total = total;
    }

    public final int getCount() {
        return count;
    }

    public final void setCount(int count) {
        this.count = count;
    }
    
}
