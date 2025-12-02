package com.invoiceGeneration.service;

import java.util.List;
import com.invoiceGeneration.entity.InvoiceItem;
import com.invoiceGeneration.entity.Invoice;

public interface InvoiceItemService {

    InvoiceItem createItem(InvoiceItem item);

    InvoiceItem getItemById(Long id);

    List<InvoiceItem> getItemsByInvoice(Invoice invoice);

    List<InvoiceItem> getAllItems();

    InvoiceItem updateItem(Long id, InvoiceItem item);

    void deleteItem(Long id);
}
