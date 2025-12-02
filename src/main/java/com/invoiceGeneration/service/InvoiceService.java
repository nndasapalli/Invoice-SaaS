package com.invoiceGeneration.service;

import java.util.List;
import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.User;
import com.invoiceGeneration.entity.Client;

public interface InvoiceService {

    Invoice createInvoice(Invoice invoice);

    Invoice getInvoiceById(Long id);

    Invoice getInvoiceByNumber(String invoiceNumber);

    List<Invoice> getInvoicesByUser(User user);

    List<Invoice> getInvoicesByClient(Client client);

    List<Invoice> getAllInvoices();

    Invoice updateInvoice(Long id, Invoice invoice);

    void deleteInvoice(Long id);
}