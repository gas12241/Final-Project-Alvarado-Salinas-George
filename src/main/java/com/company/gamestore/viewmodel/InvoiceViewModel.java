package com.company.gamestore.viewmodel;

import com.company.gamestore.model.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * InvoiceViewModel that will be used in a Game Store Application.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InvoiceViewModel implements Serializable {

    /**
     * Instance variables for the InvoiceViewModel. Includes
     * Annotations that match restraints set by the database.
     */
    @NotNull(message = "Name cannot be null.")
    @Size(max = 50, message = "Name cannot be longer than 50 characters.")
    private String name;

    @Size(max = 100, message = "Street cannot be longer than 100 characters.")
    private String street;

    @NotNull(message = "City cannot be null.")
    @Size(max = 50, message = "City cannot be longer than 50 characters.")
    private String city;

    @NotNull(message = "State cannot be null.")
    @Size(max = 20, message = "State cannot be longer than 20 characters.")
    private  String state;

    @Size(max = 10, message = "Zipcode cannot be longer than 10 characters.")
    private String zipcode;

    @NotNull(message = "Item Type cannot be null")
    @Size(max = 50, message = "Item Type cannot be longer than 50 characters.")
    private String itemType;

    @NotNull(message = "Item Id cannot be null.")
    private int itemId;

    @NotNull(message = "Quantity cannot be null.")
    private int quantity;

    /**
     * Getter for the name of the InvoiceViewModel Object.
     * @return Returns a String, the name of the InvoiceViewModel
     *         Object.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the InvoiceViewModel Object.
     * @return String taken in to set the name of the
     *         InvoiceViewModel Object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the street of the InvoiceViewModel Object.
     * @return Returns a String, the street of the InvoiceViewModel
     *         Object.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for the street of the InvoiceViewModel Object.
     * @return String taken in to set the street of the
     *         InvoiceViewModel Object.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter for the city of the InvoiceViewModel Object.
     * @return Returns a String, the city of the InvoiceViewModel
     *         Object.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the city of the InvoiceViewModel Object.
     * @return String taken in to set the city of the
     *         InvoiceViewModel Object.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for the state of the InvoiceViewModel Object.
     * @return Returns a String, the state of the InvoiceViewModel
     *         Object.
     */
    public String getState() {
        return state;
    }

    /**
     * Setter for the state of the InvoiceViewModel Object.
     * @return String taken in to set the state of the
     *         InvoiceViewModel Object.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter for the zipcode of the InvoiceViewModel Object.
     * @return Returns a String, the zipcode of the InvoiceViewModel
     *         Object.
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Setter for the zipcode of the InvoiceViewModel Object.
     * @return String taken in to set the zipcode of the
     *         InvoiceViewModel Object.
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Getter for the itemType of the InvoiceViewModel Object.
     * @return Returns a String, the itemType of the InvoiceViewModel
     *         Object.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Setter for the itemType of the InvoiceViewModel Object.
     * @return String taken in to set the itemType of the
     *         InvoiceViewModel Object.
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Getter for the itemId of the InvoiceViewModel Object.
     * @return Returns an int, the itemId of the InvoiceViewModel
     *         Object.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Setter for the itemId of the InvoiceViewModel Object.
     * @return int taken in to set the itemId of the
     *         InvoiceViewModel Object.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter for the quantity of the InvoiceViewModel Object.
     * @return Returns an int, the quantity of the InvoiceViewModel Object.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity of the InvoiceViewModel Object.
     * @return int taken in to set the quantity of the
     *         InvoiceViewModel Object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getItemId() == that.getItemId() && getQuantity() == that.getQuantity() && Objects.equals(getName(), that.getName()) && Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getState(), that.getState()) && Objects.equals(getZipcode(), that.getZipcode()) && Objects.equals(getItemType(), that.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getStreet(), getCity(), getState(), getZipcode(), getItemType(), getItemId(), getQuantity());
    }
}
