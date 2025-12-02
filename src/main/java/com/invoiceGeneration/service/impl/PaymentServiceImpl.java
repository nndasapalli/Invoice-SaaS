package com.invoiceGeneration.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.Payment;
import com.invoiceGeneration.repository.PaymentRepository;
import com.invoiceGeneration.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getPaymentsByInvoice(Invoice invoice) {
        return paymentRepository.findByInvoice(invoice);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment updatePayment(Long id, Payment updatedPayment) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setAmount(updatedPayment.getAmount());
            payment.setPaymentDate(updatedPayment.getPaymentDate());
            payment.setMode(updatedPayment.getMode());
            payment.setTransactionId(updatedPayment.getTransactionId());
            return paymentRepository.save(payment);
        }).orElse(null);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}