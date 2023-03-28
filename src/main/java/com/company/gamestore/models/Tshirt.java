package com.company.gamestore.models;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Tshirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must supply a value for id.")
    private int tshirtId;
    @NotEmpty(message = "You must supply a value for size.")
    @Size(max = 20, message = "Size can not larger than 20 characters.")
    private String size;
    @NotEmpty(message = "You must supply a value for color.")
    @Size(max = 20, message = "Color can not larger than 20 characters.")
    private String color;
    @NotEmpty(message = "You must supply a value for description.")
    @Size(max = 20, message = "Description can not larger than 255 characters.")
    private String description;
    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, message = "Price cannot be null and must be at least 0.00")
    private float price;
    @NotNull(message = "You must supply a value for quantity.")
    private int quantity;

    public int getTshirtId() {
        return tshirtId;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTshirtId(int tshirtId) {
        this.tshirtId = tshirtId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "tshirtId=" + tshirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tShirt = (Tshirt) o;
        return tshirtId == tShirt.tshirtId && Float.compare(tShirt.price, price) == 0 && quantity == tShirt.quantity && Objects.equals(size, tShirt.size) && Objects.equals(color, tShirt.color) && Objects.equals(description, tShirt.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tshirtId, size, color, description, price, quantity);
    }
}
