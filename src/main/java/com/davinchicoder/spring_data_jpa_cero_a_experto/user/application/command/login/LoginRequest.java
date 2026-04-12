package com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.login;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.application.mediator.Request;
import lombok.Data;

@Data
public class LoginRequest implements Request<LoginResponse> {
    private String email;
    private String password;
}
