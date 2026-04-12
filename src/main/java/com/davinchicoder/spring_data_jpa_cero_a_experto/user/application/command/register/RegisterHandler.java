package com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.application.mediator.RequestHandler;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.UserRole;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.AuthenticationPort;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.PasswordEncoderPort;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {

    private final UserRepository repository;
    private final PasswordEncoderPort passwordEncoderPort;
    private final AuthenticationPort authenticationPort;

    @Override
    public RegisterResponse handle(RegisterRequest request) {
        boolean existsByEmail = repository.existsByEmail(request.getEmail());
        if (existsByEmail) {
            throw new RuntimeException("User found by email, please login or provide another email");
        }

        String password = passwordEncoderPort.encode(request.getPassword());

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(password)
                .role(UserRole.USER)
                .build();

        User userUpsert = repository.upsert(user);

        String token = authenticationPort.authenticate(userUpsert.getEmail(), request.getPassword());

        return new RegisterResponse(token);
    }

    @Override
    public Class<RegisterRequest> getRequestType() {
        return RegisterRequest.class;
    }
}
