package com.invoiceGeneration.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.invoiceGeneration.entity.Invoice;
import com.invoiceGeneration.entity.InvoiceItem;
import com.invoiceGeneration.repository.InvoiceItemRepository;
import com.invoiceGeneration.service.InvoiceItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceItemServiceImpl implements InvoiceItemService {

    private final InvoiceItemRepository itemRepository;

    @Override
    public InvoiceItem createItem(InvoiceItem item) {
        return itemRepository.save(item);
    }

    @Override
    public InvoiceItem getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<InvoiceItem> getItemsByInvoice(Invoice invoice) {
        return itemRepository.findByInvoice(invoice);
    }

    @Override
    public List<InvoiceItem> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public InvoiceItem updateItem(Long id, InvoiceItem updatedItem) {
        return itemRepository.findById(id).map(item -> {
            item.setDescription(updatedItem.getDescription());
            item.setQuantity(updatedItem.getQuantity());
            item.setPrice(updatedItem.getPrice());
            item.setTotal(updatedItem.getTotal());
            return itemRepository.save(item);
        }).orElse(null);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}