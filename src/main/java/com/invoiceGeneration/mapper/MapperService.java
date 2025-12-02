package com.invoiceGeneration.mapper;

import java.util.List;

import com.invoiceGeneration.dto.*;
import com.invoiceGeneration.entity.*;

public interface MapperService {

    // User
    User toUserEntity(UserRequestDTO dto);
    UserResponseDTO toUserDTO(User user);

    // Client
    Client toClientEntity(ClientRequestDTO dto, User user);
    ClientResponseDTO toClientDTO(Client client);

    // Invoice
    Invoice toInvoiceEntity(InvoiceRequestDTO dto, User user, Client client);
    InvoiceResponseDTO toInvoiceDTO(Invoice invoice);

    // Invoice Items
    InvoiceItem toInvoiceItemEntity(InvoiceItemDTO dto, Invoice invoice);
    InvoiceItemDTO toInvoiceItemDTO(InvoiceItem item);
    List<InvoiceItemDTO> toInvoiceItemDTOList(List<InvoiceItem> items);

    // Payment
    Payment toPaymentEntity(PaymentRequestDTO dto, Invoice invoice);
    PaymentResponseDTO toPaymentDTO(Payment payment);
}