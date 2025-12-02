package com.invoiceGeneration.service;

import java.util.List;
import com.invoiceGeneration.entity.Payment;
import com.invoiceGeneration.entity.Invoice;

public interface PaymentService {

    Payment createPayment(Payment payment);

    Payment getPaymentById(Long id);

    List<Payment> getPaymentsByInvoice(Invoice invoice);

    List<Payment> getAllPayments();

    Payment updatePayment(Long id, Payment payment);

    void deletePayment(Long id);
}