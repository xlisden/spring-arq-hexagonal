package com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.login;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.AuthenticationPort;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginHandler implements RequestHandler<LoginRequest, LoginResponse> {

    private final UserRepository repository;
    private final AuthenticationPort authenticationPort;

    @Override
    public LoginResponse handle(LoginRequest request) {
        User user = repository.getByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User by email " + request.getEmail() + " not found"));

        String token  = authenticationPort.authenticate(user.getEmail(), request.getPassword());

        return new LoginResponse(token);
    }

    @Override
    public Class<LoginRequest> getRequestType() {
        return LoginRequest.class;
    }
}
