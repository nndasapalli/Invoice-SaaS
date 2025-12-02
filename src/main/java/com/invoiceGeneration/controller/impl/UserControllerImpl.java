package com.invoiceGeneration.controller.impl;

import java.util.List;

import com.invoiceGeneration.controller.interfaces.UserController;
import org.springframework.web.bind.annotation.*;

import com.invoiceGeneration.mapper.MapperService;
import com.invoiceGeneration.dto.UserRequestDTO;
import com.invoiceGeneration.dto.UserResponseDTO;
import com.invoiceGeneration.entity.User;
import com.invoiceGeneration.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user-management/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final MapperService mapperService;

    @Override
    public UserResponseDTO create(@RequestBody UserRequestDTO dto) {
        User user = mapperService.toUserEntity(dto);
        return mapperService.toUserDTO(userService.createUser(user));
    }

    @Override
    public UserResponseDTO get(@PathVariable Long id) {
        return mapperService.toUserDTO(userService.getUserById(id));
    }

    @Override
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers()
                .stream()
                .map(mapperService::toUserDTO)
                .toList();
    }

    @Override
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserRequestDTO dto) {
        User updatedEntity = mapperService.toUserEntity(dto);
        User updatedUser = userService.updateUser(id, updatedEntity);
        return mapperService.toUserDTO(updatedUser);
    }
}