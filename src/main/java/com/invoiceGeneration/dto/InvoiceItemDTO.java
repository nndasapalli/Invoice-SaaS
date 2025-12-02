package com.invoiceGeneration.dto;

import lombok.Data;

@Data
public class InvoiceItemDTO {

    private Long itemId;
    private String description;
    private Integer quantity;
    private Double price;
    private Double total;
}