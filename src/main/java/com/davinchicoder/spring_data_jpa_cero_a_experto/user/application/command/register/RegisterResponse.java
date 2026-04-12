package com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private String token;
}
