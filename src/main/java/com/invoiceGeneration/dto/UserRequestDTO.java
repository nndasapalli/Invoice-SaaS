package com.invoiceGeneration.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequestDTO {

    @Schema(description = "Username")
    private String userName;
    private String email;
    private String userPassword;
    private String companyName;
    private String phone;
    private String address;
}