package com.invoiceGeneration.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.invoiceGeneration.dto.InvoiceItemDTO;
import com.invoiceGeneration.dto.InvoiceRequestDTO;
import com.invoiceGeneration.dto.InvoiceResponseDTO;
import com.invoiceGeneration.entity.*;

public class InvoiceMapper {

    public static Invoice toEntity(InvoiceRequestDTO dto, User user, Client client) {
        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setClient(client);

        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setStatus(dto.getStatus());
        invoice.setSubTotal(dto.getSubTotal());
        invoice.setTax(dto.getTax());
        invoice.setTotalAmount(dto.getTotalAmount());
        invoice.setCreatedAt(new java.util.Date());
        return invoice;
    }

    public static InvoiceResponseDTO toDTO(Invoice invoice) {
        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setInvoiceId(invoice.getInvoiceId());
        dto.setUserId(invoice.getUser().getUserId());
        dto.setClientId(invoice.getClient().getClientId());

        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setDueDate(invoice.getDueDate());
        dto.setStatus(invoice.getStatus());
        dto.setSubTotal(invoice.getSubTotal());
        dto.setTax(invoice.getTax());
        dto.setTotalAmount(invoice.getTotalAmount());
        dto.setCreatedAt(invoice.getCreatedAt());

        if (invoice.getItems() != null) {
            List<InvoiceItemDTO> itemDTOs = invoice.getItems()
                    .stream()
                    .map(InvoiceItemMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setItems(itemDTOs);
        }

        return dto;
    }
}