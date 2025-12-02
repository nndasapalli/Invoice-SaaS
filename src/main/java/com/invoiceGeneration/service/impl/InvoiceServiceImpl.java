package com.invoiceGeneration.service.impl;

import java.util.List;

import com.invoiceGeneration.util.InvoiceNumberGeneratorService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.invoiceGeneration.entity.*;
import com.invoiceGeneration.repository.InvoiceRepository;
import com.invoiceGeneration.service.InvoiceService;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceNumberGeneratorService invoiceNumberGeneratorService;

    @Override
    public Invoice createInvoice(Invoice invoice) {
        String generatedNumber = invoiceNumberGeneratorService.generateInvoiceNumber(invoice.getUser().getUserId());
        invoice.setInvoiceNumber(generatedNumber);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice getInvoiceByNumber(String invoiceNumber) {
        return invoiceRepository.findByInvoiceNumber(invoiceNumber);
    }

    @Override
    public List<Invoice> getInvoicesByUser(User user) {
        return invoiceRepository.findByUser(user);
    }

    @Override
    public List<Invoice> getInvoicesByClient(Client client) {
        return invoiceRepository.findByClient(client);
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        return invoiceRepository.findById(id).map(invoice -> {
            invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
            invoice.setDueDate(updatedInvoice.getDueDate());
            invoice.setStatus(updatedInvoice.getStatus());
            invoice.setSubTotal(updatedInvoice.getSubTotal());
            invoice.setTax(updatedInvoice.getTax());
            invoice.setTotalAmount(updatedInvoice.getTotalAmount());
            return invoiceRepository.save(invoice);
        }).orElse(null);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}