package ch7.catalogue.persistent;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class ShoppingCartPersist<Item>
        extends shared.PersistentBase implements Serializable {
    private static final long serialVersionUID = 1L;

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();
    
    protected String accountNumber;
    private List<CatalogueItemPersist> items;
    private double total;
    private int count;
    
    public ShoppingCartPersist() {
        resetItems();
    }
    
    public final void resetItems() {
        items = new ArrayList<CatalogueItemPersist>();
        total = 0.0;
        count = 0;
    }
    
    @Pattern(regexp="[a-zA-Z]{2}\\d{3}", message="must be in the format AA999")
    @NotNull
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public void setItems(List<CatalogueItemPersist> items) {
        this.items = items;
    }
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    public List<CatalogueItemPersist> getItems() {
        return items;
    }
    
    public void addItem(CatalogueItemPersist item) {
        items.add(item);
    }
    
    public void addTotal(double amount) {
        total += amount;
    }
    
    @Transient
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
