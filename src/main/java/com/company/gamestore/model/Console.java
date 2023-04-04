package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console implements Serializable {

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

    public Integer getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(Integer consoleId) {
        this.consoleId = consoleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return Objects.equals(getConsoleId(), console.getConsoleId()) && Objects.equals(getModel(), console.getModel()) && Objects.equals(getManufacturer(), console.getManufacturer()) && Objects.equals(getMemoryAmount(), console.getMemoryAmount()) && Objects.equals(getProcessor(), console.getProcessor()) && Objects.equals(getPrice(), console.getPrice()) && Objects.equals(getQuantity(), console.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getPrice(), getQuantity());
    }

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
