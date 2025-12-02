package com.invoiceGeneration.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long userId;
    private String userName;
    private String email;
    private String companyName;
    private String phone;
    private String address;
    private Date createdAt;
}