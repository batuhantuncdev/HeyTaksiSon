package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long id;

    private Integer paymentType;
    private String paymentTypeName;
    private Long customerId;
    private Long driverId;
    private Long tripId;
}
