package ch7.catalogue;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class CatalogueItem extends shared.PersistentBase implements
        Serializable {
    private static final long serialVersionUID = 1L;

    private String itemId;
    private String name;
    private String description;
    private double price;

    public CatalogueItem() {
        this(null, "", "", 0.0);
    }

    public CatalogueItem(String itemId, String name, String description,
            double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @NotNull
    @Length(min = 1, max = 10)
    public final String getItemId() {
        return itemId;
    }

    public final void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Length(min = 1, max = 50)
    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    @Lob
    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final double getPrice() {
        return price;
    }

    public final void setPrice(double price) {
        this.price = price;
    }

}
