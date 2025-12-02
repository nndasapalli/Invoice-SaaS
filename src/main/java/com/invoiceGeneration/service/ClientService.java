package com.invoiceGeneration.service;

import java.util.List;
import com.invoiceGeneration.entity.Client;
import com.invoiceGeneration.entity.User;

public interface ClientService {

    Client createClient(Client client);

    Client getClientById(Long id);

    List<Client> getClientsByUser(User user);

    List<Client> getAllClients();

    Client updateClient(Long id, Client client);

    void deleteClient(Long id);
}