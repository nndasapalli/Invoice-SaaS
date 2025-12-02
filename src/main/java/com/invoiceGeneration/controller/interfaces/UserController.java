package com.invoiceGeneration.controller.interfaces;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.invoiceGeneration.dto.UserRequestDTO;
import com.invoiceGeneration.dto.UserResponseDTO;

@Tag(
        name = "User-management",
        description = "Operation on User management"
)
public interface UserController {

    @Operation(
            summary = "Create User",
            description = "Creates new user account"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Created Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    UserResponseDTO create(@RequestBody UserRequestDTO dto);

    @Operation(
            summary = "Get User by id",
            description = "Returns user details by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    UserResponseDTO get(@PathVariable Long id);

    @Operation(
            summary = "Get all Users",
            description = "Returns all user details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetched Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    List<UserResponseDTO> getAll();

    @Operation(
            summary = "Update User by id",
            description = "Updates and returns user details by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping("/{id}")
    UserResponseDTO update(@PathVariable Long id, @RequestBody UserRequestDTO dto);
}