package com.company.gamestore.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Tax Class that Models the information you can get from the "tax" table in the Database.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tax")
public class Tax implements Serializable {

    /**
     * Tax Objects have a Key of type String named "state"
     * that can't be longer than 2 characters.
     */
    @Id
    @Column(name = "state")
    @Size(max = 2, message = "State entry should be no more than two characters long.")
    private String state;


    @NotNull(message = "Rate cannot be null.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Tax rate cannot be null and must be above 0.00.")
    @Digits(integer = 5, fraction = 3, message = "The tax has up to 8 numbers total with 3 after the decimal")
    private BigDecimal rate;



    /**
     * Getter for the state(Id) of the Tax Object.
     * @return Returns a String, the state of the Tax Object.
     */
    public String getState() {
        return state;
    }

    /**
     * Setter for the state of the Tax Object.
     * @param state String that will be used to set the State of the Tax Object.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Getter for the rate of the Tax Object.
     * @return Returns a BigDecimal, the rate of the Tax Object.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Setter for the rate of the Tax Object.
     * @param rate BigDecimal that will be used to set the rate of the Tax Object.
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * equals() of the Tax Object. Will be used for equality.
     * @param o Object o being used for comparison.
     * @return Returns True if the Object calling the method equals
     *         the object being inputted. Else, returns false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(getState(), tax.getState()) && Objects.equals(getRate(), tax.getRate());
    }

    /**
     * hashCode() of the Tax Object. Will be used for equality.
     * @return Returns the object in int form.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getState(), getRate());
    }

    /**
     * toString() of the Tax Object. Will be used for equality.
     * @return Returns the Objects in String form.
     */
    @Override
    public String toString() {
        return "Tax{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
