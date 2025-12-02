package com.invoiceGeneration.controller.interfaces;

import com.invoiceGeneration.dto.ClientRequestDTO;
import com.invoiceGeneration.dto.ClientResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(
        name = "Client-management",
        description = "Operation on Client management"
)
public interface ClientController {
    @Operation(
            summary = "Create Client",
            description = "Create client details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    ClientResponseDTO create(@RequestBody ClientRequestDTO dto);

    @Operation(
            summary = "Get Client by id",
            description = "Returns client details by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client details retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    ClientResponseDTO get(@PathVariable Long id);

    @Operation(
            summary = "Get all Clients",
            description = "Returns all clients details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Client details retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/{userId}")
    List<ClientResponseDTO> getByUser(@PathVariable Long userId);
}
