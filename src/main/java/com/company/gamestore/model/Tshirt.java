package com.company.gamestore.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Tshirt Model that will be used in a Game Store Application.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tshirt")
public class Tshirt implements Serializable {

    /**
     * Instance variables for the Tshirt Model. Includes
     * Annotations that match restraints set by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "You must supply a value for id.")
    private int tshirtId;

    @NotEmpty(message = "You must supply a value for size.")
    @Size(max = 20, message = "Size can not be larger than 20 characters.")
    private String size;

    @NotEmpty(message = "You must supply a value for color.")
    @Size(max = 20, message = "Color can not be larger than 20 characters.")
    private String color;

    @NotEmpty(message = "You must supply a value for description.")
    @Size(max = 255, message = "Description can not larger than 255 characters.")
    private String description;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, message = "Price cannot be null and must be at least 0.00")
    @DecimalMax(value = "999.99", inclusive = true, message = "Value must be less than {value}")
    private BigDecimal price;

    @NotNull(message = "You must supply a value for quantity.")
    private int quantity;

    /**
     * Getter for the tshirtId of the Tshirt Object.
     * @return Returns an int, the tshirtId of the Tshirt Object.
     */
    public int getTshirtId() {
        return tshirtId;
    }

    /**
     * Getter for the size of the Tshirt Object.
     * @return Returns a String, the size of the Tshirt Object.
     */
    public String getSize() {
        return size;
    }

    /**
     * Getter for the color of the Tshirt Object.
     * @return Returns a String, the color of the Tshirt Object.
     */
    public String getColor() {
        return color;
    }

    /**
     * Getter for the description of the Tshirt Object.
     * @return Returns a String, the description of the Tshirt Object.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the price of the Tshirt Object.
     * @return Returns a BigDecimal, the price of the Tshirt Object.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter for the quantity of the Tshirt Object.
     * @return Returns an int, the quantity of the Tshirt Object.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the tshirtId of the Tshirt Object.
     * @param tshirtId int taken in to set the tshirtId of the Tshirt Object.
     */
    public void setTshirtId(int tshirtId) {
        this.tshirtId = tshirtId;
    }

    /**
     * Setter for the size of the Tshirt Object.
     * @param size String taken in to set the size of the Tshirt Object.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Setter for the color of the Tshirt Object.
     * @param color String taken in to set the color of the Tshirt Object.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Setter for the description of the Tshirt Object.
     * @param description String taken in to set the description
     *                    of the Tshirt Object.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for the price of the Tshirt Object.
     * @param price BigDecimal taken in to set the price of the
     *              Tshirt Object.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Setter for the quantity of the Tshirt Object.
     * @param quantity int taken in to set the quantity of the
     *                 Tshirt Object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * toString() for the Tshirt object.
     * @return Returns the Tshirt Object as a String.
     */
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

    /**
     * equals() for the Tshirt Object.
     * @param o Object o that will be used for comparison.
     * @return Returns true if the Object calling the method
     *         equals Object o, else it will return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return getTshirtId() == tshirt.getTshirtId() && getQuantity() == tshirt.getQuantity() && Objects.equals(getSize(), tshirt.getSize()) && Objects.equals(getColor(), tshirt.getColor()) && Objects.equals(getDescription(), tshirt.getDescription()) && Objects.equals(getPrice(), tshirt.getPrice());
    }

    /**
     * hashCode() for the Tshirt Object. Can be used for comparison.
     * @return Returns the Tshirt Object as an int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getTshirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
