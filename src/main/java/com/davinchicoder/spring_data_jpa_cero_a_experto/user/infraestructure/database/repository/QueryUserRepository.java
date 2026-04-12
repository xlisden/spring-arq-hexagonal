package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.repository;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueryUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
}
