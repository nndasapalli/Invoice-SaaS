package com.invoiceGeneration.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class InvoiceRequestDTO {

    private Long userId;
    private Long clientId;

    private String invoiceNumber;
    private Date invoiceDate;
    private Date dueDate;

    private String status;

    private Double subTotal;
    private Double tax;
    private Double totalAmount;

    private List<InvoiceItemDTO> items;
}