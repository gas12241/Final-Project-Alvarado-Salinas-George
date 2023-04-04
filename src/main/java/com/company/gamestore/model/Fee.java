package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Fee Class that Models the information you can get
 * from the "fee" table in the Database
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "fee")
public class Fee implements Serializable {

    /**
     * Fee Objects have a Key of type String named "product_type"
     * that can't be longer than 50 characters.
     */
    @Id
    @Column(name = "product_type")
    @Size(max = 50, message = "Product type can not be larger than 50 characters")
    private String productType;

    @NotNull(message = "Fee cannot be null.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Fee cannot be less than 0.00")
    @Digits(integer = 6, fraction = 2, message = "Fee cannot be bigger than 8 numbers or have more than 2 after the decimal.") // Got from this https://stackoverflow.com/questions/65490099/how-to-use-digits-validation-on-integer-value
    private BigDecimal fee;

    /**
     * Empty constructor. Instance variables will be set later.
     */
    public Fee() {
    }

    /**
     * Getter for the productType(Id) of the Fee Object.
     * @return Returns a String, the productType of the Fee Object.
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Setter for the productType(Id) of the Fee Object.
     * @param productType Takes in a String that will be used to set
     *                    the productType of the Fee Object.
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Getter for the fee of the Fee Object.
     * @return Returns a BigDecimal, the fee of the Fee Object.
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * Setter for the fee of the Fee Object.
     * @param fee Takes in a String that will be used to
     *            set the fee of the Fee Object.
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * equals() for the Fee Object.
     * @param o Object o which will be used for comparison.
     * @return Returns true if the object calling this method is
     *         equal to the inputted Object. False otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fee fee1 = (Fee) o;
        return Objects.equals(getProductType(), fee1.getProductType()) && Objects.equals(getFee(), fee1.getFee());
    }

    /**
     * hashCode() for the Fee Object. Will be used to test for equality.
     * @return Returns the Fee Object in int form.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }

    /**
     * toString() for the Fee Object. Will be used to test for equality.
     * @return Returns the Fee Object in String form.
     */
    @Override
    public String toString() {
        return "Fee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
