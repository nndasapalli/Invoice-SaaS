package com.invoiceGeneration.mapper;

import com.invoiceGeneration.dto.ClientRequestDTO;
import com.invoiceGeneration.dto.ClientResponseDTO;
import com.invoiceGeneration.entity.Client;
import com.invoiceGeneration.entity.User;

public class ClientMapper {

    public static Client toEntity(ClientRequestDTO dto, User user) {
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

    public static ClientResponseDTO toDTO(Client client) {
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
}