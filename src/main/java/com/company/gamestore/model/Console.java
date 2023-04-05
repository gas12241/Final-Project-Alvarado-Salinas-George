package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Console Model that will be used in a Game Store Application.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console implements Serializable {

    /**
     * Instance variables for the Console Model. Includes
     * Annotations that match restraints set by the database.
     */
    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Console_id cannot be null.")
    private Integer consoleId;

    @NotNull(message = "Model cannot be null.")
    @Size(max = 50, message = "Model can not be larger than 50 characters.")
    private String model;

    @NotNull(message = "Manufacturer cannot be null.")
    @Size(max = 50, message = "Manufacturer can not be larger than 50 characters.")
    private String manufacturer;

    @Column(name = "memory_amount")
    @Size(max = 20, message = "Memory Amount can not be larger than 20 characters.")
    private String memoryAmount;

    @Size(max = 20, message = "Processor can not be larger than 20 characters.")
    private String processor;

    @NotNull(message = "Price cannot be null.")
    @DecimalMax(value = "999.99", inclusive = true, message = "Price must be less than 1,000.00")
    @DecimalMin(value = "0.00", inclusive = true, message = "Price cannot be less than 0.00")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null.")
    @Max(value = 999, message = "You can only order less than 1000 consoles")
    @Min(value = 0, message = "You cannot have negative consoles")
    private Integer quantity;

    /**
     * Getter for the consoleId of the Console Object.
     * @return Returns an Integer, the consoleId of the Console Object.
     */
    public Integer getConsoleId() {
        return consoleId;
    }

    /**
     * Setter for the consoleId of the Console Object.
     * @param consoleId Integer taken in to set the consoleId of the Console Object.
     */
    public void setConsoleId(Integer consoleId) {
        this.consoleId = consoleId;
    }

    /**
     * Getter for the model of the Console Object.
     * @return Returns a String, the model of the Console Object.
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter for the model of the Console Object.
     * @param model String taken in to set the model of the Console Object.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for the manufacturer of the Console Object.
     * @return Returns a String, the manufacturer. of the Console Object.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Setter for the manufacturer of the Console Object.
     * @param manufacturer String taken in to set the manufacturer of the Console Object.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Getter for the memoryAmount of the Console Object.
     * @return Returns a String, the memoryAmount of the Console Object.
     */
    public String getMemoryAmount() {
        return memoryAmount;
    }

    /**
     * Setter for the memoryAmount of the Console Object.
     * @param memoryAmount String taken in to set the memoryAmount of the Console Object.
     */
    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    /**
     * Getter for the processor of the Console Object.
     * @return Returns a String, the processor of the Console Object.
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * Setter for the process of the Console Object.
     * @param processor String taken in to set the processor of
     *                  the Console Object.
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     * Getter for the price of the Console Object.
     * @return Returns a BigDecimal, the price of the Console Object.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter for the price of the Console Object.
     * @param price BigDecimal taken in to set the price of the
     *              Console Object.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Getter for the quantity of the Console Object.
     * @return Returns an Integer, the quantity of the Console Object.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Setter for the quantity of the Console Object.
     * @param quantity Integer taken in to set the quantity of the
     *                 Console Object.
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * equals() for the Console Object. Used for comparison.
     * @param o Object o that will be used for comparison.
     * @return Returns true if the Object calling the method
     *         equals Object o, else it will return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return Objects.equals(getConsoleId(), console.getConsoleId()) && Objects.equals(getModel(), console.getModel()) && Objects.equals(getManufacturer(), console.getManufacturer()) && Objects.equals(getMemoryAmount(), console.getMemoryAmount()) && Objects.equals(getProcessor(), console.getProcessor()) && Objects.equals(getPrice(), console.getPrice()) && Objects.equals(getQuantity(), console.getQuantity());
    }

    /**
     * hashCode() for the Console Object. Can be used for comparison.
     * @return Returns the Console Object as an int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getPrice(), getQuantity());
    }

    /**
     * toString() for the Console Object.
     * @return Returns the Console Object as a String.
     */
    @Override
    public String toString() {
        return "Console{" +
                "consoleId=" + consoleId +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
