package com.invoiceGeneration.mapper;

import com.invoiceGeneration.dto.PaymentRequestDTO;
import com.invoiceGeneration.dto.PaymentResponseDTO;
import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.Payment;

public class PaymentMapper {

    public static Payment toEntity(PaymentRequestDTO dto, Invoice invoice) {
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setMode(dto.getMode());
        payment.setTransactionId(dto.getTransactionId());
        return payment;
    }

    public static PaymentResponseDTO toDTO(Payment payment) {
        PaymentResponseDTO dto = new PaymentResponseDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setInvoiceId(payment.getInvoice().getInvoiceId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setMode(payment.getMode());
        dto.setTransactionId(payment.getTransactionId());
        return dto;
    }
}
