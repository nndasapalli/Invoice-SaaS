package com.invoiceGeneration.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ClientResponseDTO {

    private Long clientId;
    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String billingAddress;
    private String gstNumber;
    private Date createdAt;
}