package com.invoiceGeneration.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.invoiceGeneration.entity.Client;
import com.invoiceGeneration.entity.User;
import com.invoiceGeneration.repository.ClientRepository;
import com.invoiceGeneration.service.ClientService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> getClientsByUser(User user) {
        return clientRepository.findByUser(user);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id).map(client -> {
            client.setName(updatedClient.getName());
            client.setEmail(updatedClient.getEmail());
            client.setPhone(updatedClient.getPhone());
            client.setBillingAddress(updatedClient.getBillingAddress());
            client.setGstNumber(updatedClient.getGstNumber());
            return clientRepository.save(client);
        }).orElse(null);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}