package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
