package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.mapper;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.entity.User;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    User mapToUser(UserEntity entity);

    @Mapping(target = "authorities", ignore = true)
    UserEntity mapToEntity(User user);
}
