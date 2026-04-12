package com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.User;

public interface AuthenticationPort {
    String authenticate(String username, String password);
}
