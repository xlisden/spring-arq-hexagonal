package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.mapper;

import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.login.LoginRequest;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.login.LoginResponse;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register.RegisterRequest;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register.RegisterResponse;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.LoginRequestDto;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.RegisterRequestDto;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.TokenResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    LoginRequest mapToLogin(LoginRequestDto dto);

    RegisterRequest mapToRegister(RegisterRequestDto dto);

    TokenResponseDto mapToDto(LoginResponse response);

    TokenResponseDto mapToDto(RegisterResponse response);
}
