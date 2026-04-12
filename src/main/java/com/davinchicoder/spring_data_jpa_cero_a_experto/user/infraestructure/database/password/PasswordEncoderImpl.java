package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.password;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.PasswordEncoderPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordEncoderImpl implements PasswordEncoderPort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
