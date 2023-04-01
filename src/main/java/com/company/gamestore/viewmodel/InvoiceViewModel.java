package com.company.gamestore.viewmodel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class InvoiceViewModel {
    @NotNull
    @Size(max = 50)
    private String name;
    @NotEmpty
    @Size(max = 100)
    private String street;
    @NotEmpty
    @Size(max = 50)
    private String city;
    @NotEmpty
    @Size(max = 20)
    private  String state;
    @NotEmpty
    @Size(max = 10)
    private String zipcode;
    @NotEmpty
    @Size(max = 50)
    private String itemType;
    @NotNull
    private int itemId;
    @NotNull
    private int quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return itemId == that.itemId && quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(itemType, that.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, city, state, zipcode, itemType, itemId, quantity);
    }
}
