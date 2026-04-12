package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {

    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
