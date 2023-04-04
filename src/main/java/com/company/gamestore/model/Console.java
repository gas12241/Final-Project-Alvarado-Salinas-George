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
    private Integer console_id;

    @NotNull(message = "Model cannot be null.")
    @Size(max = 50, message = "Model can not be larger than 50 characters.")
    private String model;

    @NotNull(message = "Manufacturer cannot be null.")
    @Size(max = 50, message = "Manufacturer can not be larger than 50 characters.")
    private String manufacturer;

    @Size(max = 20, message = "Memory Amount can not be larger than 20 characters.")
    private String memory_amount;

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

    public Integer getConsole_id() {
        return console_id;
    }

    public void setConsole_id(Integer console_id) {
        this.console_id = console_id;
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

    public String getMemory_amount() {
        return memory_amount;
    }

    public void setMemory_amount(String memory_amount) {
        this.memory_amount = memory_amount;
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
        return Objects.equals(console_id, console.console_id) && model.equals(console.model) && manufacturer.equals(console.manufacturer) && Objects.equals(memory_amount, console.memory_amount) && Objects.equals(processor, console.processor) && price.equals(console.price) && Objects.equals(quantity, console.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(console_id, model, manufacturer, memory_amount, processor, price, quantity);
    }

    @Override
    public String toString() {
        return "Console{" +
                "console_id=" + console_id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memory_amount='" + memory_amount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
