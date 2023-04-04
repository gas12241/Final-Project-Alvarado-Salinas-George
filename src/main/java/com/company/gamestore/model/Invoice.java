package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="invoice")
public class Invoice implements Serializable {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int invoiceId;

    @NotNull (message = "Name cannot be null.")
    @Size(max = 50, message = "Name cannot be null or longer than 50 characters.")
    private String name;

    @Size(max = 100, message = "Street cannot be longer than 100 characters.")
    private String street;

    @NotNull(message = "City cannot be null.")
    @Size(max = 50, message = "City can not be longer than 50 characters.")
    private String city;

    @NotNull(message = "state cannot be null")
    @Size(max = 20, message = "State can not be longer than 20 Characters")
    private  String state;

    @Size(max = 10, message = "Zipcode can not be longer than 10 characters.")
    private String zipcode;

    @NotNull(message = "Item Type cannot be null.")
    @Size(max = 50, message = "Item Type cannot be longer than 50 characters.")
    private String itemType;


    @NotNull(message = "Item Id cannot be null.")
    private int itemId;

    @NotNull(message = "Unit Price cannot be null.")
    @Digits(integer=6, fraction=2, message = "Unit Price cannot have more then 8 numbers or more than two after the decimal.")
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity cannot be null.")
    private int quantity;

    @NotNull(message = "Sub Total cannot be null.")
    @Digits(integer=6, fraction=2, message = "Sub Total cannot have more then 8 numbers or more than two after the decimal.")
    private BigDecimal subtotal;

    @NotNull(message = "Tax cannot be null.")
    @Digits(integer=6, fraction=2, message = "Tax cannot have more then 8 numbers or more than two after the decimal")
    private BigDecimal tax;

    @NotNull(message = "Processing Fee cannot be null.")
    @Digits(integer=6, fraction=2, message = "Processing fee cannot be bigger than 8 numbers with two after the decimal")
    private BigDecimal processingFee;

    @NotNull(message = "Total cannot be null.")
    @Digits(integer=6, fraction=2, message = "Total cannot be bigger than 8 numbers with two after the decimal")
    private BigDecimal total;



    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getQuantity() == invoice.getQuantity() &&
                Objects.equals(getName(), invoice.getName()) &&
                Objects.equals(getStreet(), invoice.getStreet()) &&
                Objects.equals(getCity(), invoice.getCity()) &&
                Objects.equals(getState(), invoice.getState()) &&
                Objects.equals(getZipcode(), invoice.getZipcode()) &&
                Objects.equals(getItemType(), invoice.getItemType()) &&
                Objects.equals(getItemId(), invoice.getItemId()) &&
                Objects.equals(getUnitPrice(), invoice.getUnitPrice()) &&
                Objects.equals(getSubtotal(), invoice.getSubtotal()) &&
                Objects.equals(getTax(), invoice.getTax()) &&
                Objects.equals(getProcessingFee(), invoice.getProcessingFee()) &&
                Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(),
                getName(),
                getStreet(),
                getCity(),
                getState(),
                getZipcode(),
                getItemType(),
                getItemId(),
                getUnitPrice(),
                getQuantity(),
                getSubtotal(),
                getTax(),
                getProcessingFee(),
                getTotal());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
