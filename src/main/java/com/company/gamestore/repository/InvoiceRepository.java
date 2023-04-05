package com.company.gamestore.repository;


import com.company.gamestore.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * InvoiceRepository that will be used in a Game Store Application.
 * Allows you to access the Database and work with the "invoice" table.
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    /**
     * Custom Method to find Invoices with a given name.
     * @param name used to find Invoices with the name attached.
     * @return Returns a List of Invoices for a given customer
     *         by their name.
     */
    List<Invoice> findByName(String name);
}
