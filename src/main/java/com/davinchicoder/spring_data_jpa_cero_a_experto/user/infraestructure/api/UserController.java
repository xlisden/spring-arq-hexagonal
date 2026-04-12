package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.application.mediator.Mediator;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.login.LoginRequest;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.login.LoginResponse;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register.RegisterRequest;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.application.command.register.RegisterResponse;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.LoginRequestDto;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.RegisterRequestDto;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.TokenResponseDto;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final Mediator mediator;
    private final UserMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto dto) {
        LoginRequest request = mapper.mapToLogin(dto);
        LoginResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(mapper.mapToDto(response));
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDto> register(@Valid @RequestBody RegisterRequestDto dto) {
        RegisterRequest request = mapper.mapToRegister(dto);
        RegisterResponse response = mediator.dispatch(request);
        return ResponseEntity.ok(mapper.mapToDto(response));
    }
}
