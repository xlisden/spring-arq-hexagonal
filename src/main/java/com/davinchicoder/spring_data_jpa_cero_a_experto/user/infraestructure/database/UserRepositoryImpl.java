package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.UserRepository;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.entity.UserEntity;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.mapper.UserEntityMapper;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.repository.QueryUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final QueryUserRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User upsert(User user) {
        UserEntity entity = mapper.mapToEntity(user);
        UserEntity save = repository.save(entity);
        return mapper.mapToUser(save);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.findByEmail(email).isPresent();
    }

    @Override
    public Optional<User> getByEmail(String email) {
        Optional<UserEntity> entity = repository.findByEmail(email);
        return entity.map(mapper::mapToUser);
    }
}
