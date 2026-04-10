package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDto {
    private String token;
}
