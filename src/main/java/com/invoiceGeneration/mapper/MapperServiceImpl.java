package com.invoiceGeneration.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.invoiceGeneration.dto.*;
import com.invoiceGeneration.entity.*;

@Service
public class MapperServiceImpl implements MapperService {

    // ----------------- USER ------------------
    @Override
    public User toUserEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setUserPassword(dto.getUserPassword());
        user.setCompanyName(dto.getCompanyName());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setCreatedAt(new java.util.Date());
        return user;
    }

    @Override
    public UserResponseDTO toUserDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setUserName(user.getUserName());
        dto.setEmail(user.getEmail());
        dto.setCompanyName(user.getCompanyName());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }

    // ----------------- CLIENT ------------------
    @Override
    public Client toClientEntity(ClientRequestDTO dto, User user) {
        Client client = new Client();
        client.setUser(user);
        client.setName(dto.getName());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());
        client.setBillingAddress(dto.getBillingAddress());
        client.setGstNumber(dto.getGstNumber());
        client.setCreatedAt(new java.util.Date());
        return client;
    }

    @Override
    public ClientResponseDTO toClientDTO(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setClientId(client.getClientId());
        dto.setUserId(client.getUser().getUserId());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPhone(client.getPhone());
        dto.setBillingAddress(client.getBillingAddress());
        dto.setGstNumber(client.getGstNumber());
        dto.setCreatedAt(client.getCreatedAt());
        return dto;
    }

    // ----------------- INVOICE ------------------
    @Override
    public Invoice toInvoiceEntity(InvoiceRequestDTO dto, User user, Client client) {
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

    @Override
    public InvoiceResponseDTO toInvoiceDTO(Invoice invoice) {

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
            dto.setItems(
                invoice.getItems()
                       .stream()
                       .map(this::toInvoiceItemDTO)
                       .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // ----------------- INVOICE ITEMS ------------------
    @Override
    public InvoiceItem toInvoiceItemEntity(InvoiceItemDTO dto, Invoice invoice) {
        InvoiceItem item = new InvoiceItem();
        item.setInvoice(invoice);
        item.setDescription(dto.getDescription());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());

        // Auto calculation
        item.setTotal(dto.getQuantity() * dto.getPrice());

        return item;
    }

    @Override
    public InvoiceItemDTO toInvoiceItemDTO(InvoiceItem item) {
        InvoiceItemDTO dto = new InvoiceItemDTO();
        dto.setItemId(item.getItemId());
        dto.setDescription(item.getDescription());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        dto.setTotal(item.getTotal());
        return dto;
    }

    @Override
    public List<InvoiceItemDTO> toInvoiceItemDTOList(List<InvoiceItem> items) {
        return items.stream().map(this::toInvoiceItemDTO).collect(Collectors.toList());
    }

    // ----------------- PAYMENT ------------------
    @Override
    public Payment toPaymentEntity(PaymentRequestDTO dto, Invoice invoice) {
        Payment payment = new Payment();
        payment.setInvoice(invoice);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setMode(dto.getMode());
        payment.setTransactionId(dto.getTransactionId());
        return payment;
    }

    @Override
    public PaymentResponseDTO toPaymentDTO(Payment payment) {
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