package com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port;

public interface PasswordEncoderPort {
    String encode(String password);
}
