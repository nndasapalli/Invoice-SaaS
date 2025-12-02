package com.invoiceGeneration.mapper;

import com.invoiceGeneration.dto.UserRequestDTO;
import com.invoiceGeneration.dto.UserResponseDTO;
import com.invoiceGeneration.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setUserPassword(dto.getUserPassword());
        user.setCompanyName(dto.getCompanyName());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
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
}