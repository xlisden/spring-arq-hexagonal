package com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.application.mediator.Request;
import lombok.Data;

@Data
public class RegisterRequest implements Request<RegisterResponse> {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
