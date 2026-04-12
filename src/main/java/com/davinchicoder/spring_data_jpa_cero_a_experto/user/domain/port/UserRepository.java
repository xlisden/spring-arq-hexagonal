package com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    User upsert(User user);

    boolean existsByEmail(String email);

    Optional<User> getByEmail(String email);
}
