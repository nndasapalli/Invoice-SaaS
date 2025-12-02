package com.invoiceGeneration.controller.impl;

import com.invoiceGeneration.controller.interfaces.InvoiceController;
import com.invoiceGeneration.util.PdfGeneratorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.invoiceGeneration.mapper.MapperService;
import com.invoiceGeneration.dto.InvoiceRequestDTO;
import com.invoiceGeneration.dto.InvoiceResponseDTO;
import com.invoiceGeneration.entity.*;
import com.invoiceGeneration.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/invoice-management/invoices")
@RequiredArgsConstructor
public class InvoiceControllerImpl implements InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceItemService itemService;
    private final UserService userService;
    private final ClientService clientService;
    private final MapperService mapperService;
    private final PdfGeneratorService pdfGeneratorService;

    @Override
    public InvoiceResponseDTO create(@RequestBody InvoiceRequestDTO dto) {

        User user = userService.getUserById(dto.getUserId());
        Client client = clientService.getClientById(dto.getClientId());

        // Map invoice
        Invoice invoice = mapperService.toInvoiceEntity(dto, user, client);
        Invoice saved = invoiceService.createInvoice(invoice);

        // Map items
        dto.getItems().forEach(itemDTO -> {
            InvoiceItem item = mapperService.toInvoiceItemEntity(itemDTO, saved);
            itemService.createItem(item);
        });

        saved.setItems(itemService.getItemsByInvoice(saved));

        return mapperService.toInvoiceDTO(saved);
    }

    @Override
    public InvoiceResponseDTO get(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        invoice.setItems(itemService.getItemsByInvoice(invoice));
        return mapperService.toInvoiceDTO(invoice);
    }

    @Override
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        invoice.setItems(itemService.getItemsByInvoice(invoice));

        byte[] pdf = pdfGeneratorService.generateInvoicePdf(invoice);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice-" + invoice.getInvoiceNumber() + ".pdf");
        headers.set(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

}