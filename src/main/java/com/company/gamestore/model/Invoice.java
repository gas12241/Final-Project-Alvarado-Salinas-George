package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
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

    @NotNull(message = "name cannot be null.")
    @Size(max = 50, message = "Name cannot be more than 50 characters.")
    private String name;

    @Size(max = 100, message = "Street cannot be more than 50 characters.")
    private String street;

    @NotNull(message = "City cannot be null.")
    @Size(max = 50, message = "City cannot be more than 50 characters.")
    private String city;

    @NotNull(message = "State cannot be null.")
    @Size(max = 20, message = "State cannot be more than 20 characters.")
    private  String state;


    @Size(max = 10, message = "Zipcode cannot be more than 10 characters")
    private String zipcode;

    @NotNull(message = "Item Type cannot be null.")
    @Size(max = 50, message = "Item Type cannot be more than 50 characters.")
    private String itemType;

    @NotNull(message = "Item Id cannot be null.")
    private int itemId;

    @NotNull(message = "Unit Price cannot be null.")
    @Digits(integer = 6, fraction = 2, message = "Unit Price has to be less than 8 numbers and only have up to 2 decimal places.")
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity cannot be null.")
    private int quantity;

    @NotNull(message = "Subtotal cannot be null.")
    @Digits(integer = 6, fraction = 2, message = "SubTotal can have up to 8 numbers including up to 2 decimal places.")
    private BigDecimal subtotal;


    @DecimalMin(value = "0.01", inclusive = true, message = "Price cannot be null and must be at least 0.01")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than {value}")
    @NotNull(message = "Tax cannot be null.")
    private BigDecimal tax;

    @DecimalMin(value = "0.01", inclusive = true, message = "Price cannot be null and must be at least 0.01")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than {value}")
    @NotNull(message = "Processing Fee cannot be null.")
    private BigDecimal processingFee;

    @DecimalMin(value = "0.01", inclusive = true, message = "Price cannot be null and must be at least 0.01")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than {value}")
    @NotNull(message = "Total cannot be null.")
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
