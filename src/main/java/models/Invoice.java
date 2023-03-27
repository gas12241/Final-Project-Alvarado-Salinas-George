package com.company.gamestore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="invoice")
public class Invoice {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceId;

    @NotNull
    @Size(max = 50)
    private String name;

    @Null
    @Size(max = 100)
    private String street;

    @NotNull
    @Size(max = 50)
    private String city;

    @NotNull
    @Size(max = 20)
    private  String state;

    @Null
    @Size(max = 10)
    private String zipcode;

    @NotNull
    @Size(max = 50)
    private String itemType;

    @NotNull
    private String itemId;

    @NotNull
    private BigDecimal unitPrice;

    @NotNull
    private int quantity;

    @NotNull
    private BigDecimal subtotal;

    @NotNull
    private BigDecimal tax;

    @NotNull
    private BigDecimal processingFee;

    @NotNull
    private BigDecimal total;

}
