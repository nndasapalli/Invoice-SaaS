package com.invoiceGeneration.dto;

import java.util.Date;
import lombok.Data;

@Data
public class PaymentRequestDTO {

    private Long invoiceId;
    private Double amount;
    private Date paymentDate;
    private String mode;
    private String transactionId;
}
