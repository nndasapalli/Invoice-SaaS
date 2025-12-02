package com.invoiceGeneration.controller.interfaces;

import com.invoiceGeneration.dto.InvoiceRequestDTO;
import com.invoiceGeneration.dto.InvoiceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Invoice-management",
        description = "Operation on invoice management"
)
public interface InvoiceController {
    @Operation(
            summary = "Create Invoice",
            description = "Insert client Invoice details"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice details inserted Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    InvoiceResponseDTO create(@RequestBody InvoiceRequestDTO dto);

    @Operation(
            summary = "Get Invoice detsils by id",
            description = "Returns Invoice details by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice details fetched Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    InvoiceResponseDTO get(@PathVariable Long id);

    @Operation(
            summary = "Get Invoice by id",
            description = "Downloads Invoice details by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Invoice downloaded Successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}/pdf")
    ResponseEntity<byte[]> downloadPdf(@PathVariable Long id);
}
