package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteResponseDTO {
    private Boolean result;
    private String description;
}
