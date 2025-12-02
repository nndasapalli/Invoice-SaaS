package com.invoiceGeneration.controller.impl;

import java.util.List;

import com.invoiceGeneration.controller.interfaces.ClientController;
import org.springframework.web.bind.annotation.*;

import com.invoiceGeneration.mapper.MapperService;
import com.invoiceGeneration.dto.ClientRequestDTO;
import com.invoiceGeneration.dto.ClientResponseDTO;
import com.invoiceGeneration.entity.Client;
import com.invoiceGeneration.entity.User;
import com.invoiceGeneration.service.ClientService;
import com.invoiceGeneration.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/client-management/clients")
@RequiredArgsConstructor
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;
    private final UserService userService;
    private final MapperService mapperService;

   @Override
    public ClientResponseDTO create(@RequestBody ClientRequestDTO dto) {
        User user = userService.getUserById(dto.getUserId());
        Client client = mapperService.toClientEntity(dto, user);
        return mapperService.toClientDTO(clientService.createClient(client));
    }

    @Override
    public ClientResponseDTO get(@PathVariable Long id) {
        return mapperService.toClientDTO(clientService.getClientById(id));
    }

    @Override
    public List<ClientResponseDTO> getByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return clientService.getClientsByUser(user)
                .stream()
                .map(mapperService::toClientDTO)
                .toList();
    }
}