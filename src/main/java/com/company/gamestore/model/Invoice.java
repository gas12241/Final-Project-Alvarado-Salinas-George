package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;


/**
 * Invoice Model that will be used in a Game Store Application.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="invoice")
public class Invoice implements Serializable {

    /**
     * Instance variables for the Invoice Model. Includes
     * Annotations that match restraints set by the database.
     */
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private int invoiceId;

    @NotNull(message = "Name cannot be null.")
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

    /**
     * Getter for the invoiceId of the Invoice Object.
     * @return Returns an int, the invoiceId of the Invoice Object.
     */
    public int getInvoiceId() {
        return invoiceId;
    }

    /**
     * Setter for the invoiceId of the Invoice Object.
     * @param invoiceId int taken in to set the invoiceId
     *                  of the Invoice Object.
     */
    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    /**
     * Getter for the name of the Invoice Object.
     * @return Returns a String, the name of the Invoice Object.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the Invoice Object.
     * @param name String taken in to set the name of the Invoice Object.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the street of the Invoice Object.
     * @return Returns a String, the street of the Invoice Object.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Setter for the street of the Invoice Object.
     * @param street String taken in to set the street of the
     *               Invoice Object.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Getter for the city of the Invoice Object.
     * @return Returns a String, the city of the Invoice Object.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the city of the Invoice Object.
     * @param city String taken in to set the city of the
     *             Invoice Object.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for the state of the Invoice Object.
     * @return Returns a String, the state of the Invoice Object.
     */
    public String getState() {
        return state;
    }

    /**
     * Setter for the state of the Invoice Object.
     * @param state String taken in to set the state of the
     *              Invoice Object.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter for the zipcode of the Invoice Object.
     * @return Returns a String, the zipcode of the Invoice Object.
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Setter for the zipcode of the Invoice Object.
     * @param zipcode String taken in to set the zipcode of the
     *                Invoice Object.
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Getter for the itemType of the Invoice Object.
     * @return Returns a String, the itemType of the Invoice Object.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Setter for the itemType of the Invoice Object.
     * @param itemType String taken in to set the itemType of the
     *                 Invoice Object.
     */
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    /**
     * Getter for the itemId of the Invoice Object.
     * @return Returns a String, the itemId of the Invoice Object.
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Setter for the itemId of the Invoice Object.
     * @param itemId int taken in to set the itemId of the
     *               Invoice Object.
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Getter for the unitPrice of the Invoice Object.
     * @return Returns a BigDecimal, the unitPrice of the Invoice Object.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Setter for the unitPrice of the Invoice Object.
     * @param unitPrice BigDecimal taken in to set the unitPrice of
     *                  the Invoice Object.
     */
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Getter for the quantity of the Invoice Object.
     * @return Returns an int, the quantity of the Invoice Object.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity of the Invoice Object.
     * @param quantity int taken in to set the quantity of the Invoice Object.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for the subtotal of the Invoice Object.
     * @return Returns a BigDecimal, the subtotal of the Invoice Object.
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Setter for the subtotal of the Invoice Object.
     * @param subtotal BigDecimal taken in to set the subtotal
     *                 of the Invoice Object.
     */
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Getter for the tax of the Invoice Object.
     * @return Returns a BigDecimal, the tax of the Invoice Object.
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * Setter for the tax of the Invoice Object.
     * @param tax BigDecimal taken in to set the tax of the
     *            Invoice Object.
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * Getter for the processingFee of the Invoice Object.
     * @return Returns a BigDecimal, the processingFee
     *         of the Invoice Object.
     */
    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    /**
     * Setter for the processingFee of the Invoice Object.
     * @param processingFee BigDecimal taken in to set the
     *                      processingFee of the Invoice Object.
     */
    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    /**
     * Getter for the total of the Invoice Object.
     * @return Returns a BigDecimal, the total of the Invoice Object.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Setter for the total of the Invoice Object.
     * @param total BigDecimal taken in to set the total of
     *              the Invoice Object.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * equals() for the Invoice Object. Will be used for comparison.
     * @param o Object o being used for comparison.
     * @return Returns true if the Object calling the method is
     *         equal to Object o.
     */
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

    /**
     * hashCode() for the Invoice Object. Can be used for comparison.
     * @return Returns the Invoice Object as an int.
     */
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

    /**
     * toString() for the Invoice Object.
     * @return Returns the Invoice Objet as a String.
     */
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
