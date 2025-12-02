package com.invoiceGeneration.controller.interfaces;

import com.invoiceGeneration.dto.PaymentRequestDTO;
import com.invoiceGeneration.dto.PaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Payment-management",
        description = "Operation on payment management"
)
public interface PaymentController {

    @Operation(
            summary = "Create Payment",
            description = "Make Payment"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment Successful"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    PaymentResponseDTO create(@RequestBody PaymentRequestDTO dto);

    @Operation(
            summary = "Get Payment by id",
            description = "Returns payment details by id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch payment Successful"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    PaymentResponseDTO get(@PathVariable Long id);
}
