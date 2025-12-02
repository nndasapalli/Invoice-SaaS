package com.invoiceGeneration.mapper;

import com.invoiceGeneration.dto.InvoiceItemDTO;
import com.invoiceGeneration.entity.InvoiceItem;
import com.invoiceGeneration.entity.Invoice;

public class InvoiceItemMapper {

    public static InvoiceItem toEntity(InvoiceItemDTO dto, Invoice invoice) {
        InvoiceItem item = new InvoiceItem();
        item.setInvoice(invoice);
        item.setDescription(dto.getDescription());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        item.setTotal(dto.getTotal());
        return item;
    }

    public static InvoiceItemDTO toDTO(InvoiceItem item) {
        InvoiceItemDTO dto = new InvoiceItemDTO();
        dto.setItemId(item.getItemId());
        dto.setDescription(item.getDescription());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setTotal(item.getTotal());
        return dto;
    }
}