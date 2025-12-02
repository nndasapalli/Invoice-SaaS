package com.invoiceGeneration.controller.impl;

import com.invoiceGeneration.controller.interfaces.PaymentController;
import org.springframework.web.bind.annotation.*;

import com.invoiceGeneration.mapper.MapperService;
import com.invoiceGeneration.dto.PaymentRequestDTO;
import com.invoiceGeneration.dto.PaymentResponseDTO;
import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.Payment;
import com.invoiceGeneration.service.InvoiceService;
import com.invoiceGeneration.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment-management/payments")
@RequiredArgsConstructor
public class PaymentControllerImpl implements PaymentController {

    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final MapperService mapperService;

    @Override
    public PaymentResponseDTO create(@RequestBody PaymentRequestDTO dto) {
        Invoice invoice = invoiceService.getInvoiceById(dto.getInvoiceId());
        Payment payment = mapperService.toPaymentEntity(dto, invoice);
        return mapperService.toPaymentDTO(paymentService.createPayment(payment));
    }

    @Override
    public PaymentResponseDTO get(@PathVariable Long id) {
        return mapperService.toPaymentDTO(paymentService.getPaymentById(id));
    }
}