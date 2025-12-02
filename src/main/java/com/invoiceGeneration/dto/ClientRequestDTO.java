package com.invoiceGeneration.dto;

import lombok.Data;

@Data
public class ClientRequestDTO {

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String billingAddress;
    private String gstNumber;
}
