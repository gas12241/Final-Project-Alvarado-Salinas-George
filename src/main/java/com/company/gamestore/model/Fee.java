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

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "fee")
public class Fee implements Serializable {

    @Id
    @Column(name = "product_type")
    @Size(max = 50, message = "Product type value can not be larger than 50 characters")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private String productType;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true, message = "Fee cannot be less than 0.00")
    @Digits(integer = 6, fraction = 2, message = "The fee can have a total of 8 numbers WITH 2 of them being after the decimal") // Got from this https://stackoverflow.com/questions/65490099/how-to-use-digits-validation-on-integer-value
    private BigDecimal fee;

    public Fee() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fee fee1 = (Fee) o;
        return Objects.equals(getProductType(), fee1.getProductType()) && Objects.equals(getFee(), fee1.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }

    @Override
    public String toString() {
        return "Fee{" +
                "productType='" + productType + '\'' +
                ", fee=" + fee +
                '}';
    }
}
