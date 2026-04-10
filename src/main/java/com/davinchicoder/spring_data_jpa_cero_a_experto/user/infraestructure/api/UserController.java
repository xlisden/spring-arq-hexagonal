package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.infrastructure.service.JwtService;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.LoginRequestDto;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.api.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username(requestDto.getEmail())
                .password(requestDto.getPassword())
                .build();
        LoginResponseDto response = new LoginResponseDto(jwtService.genToken(userDetails));
        return ResponseEntity.ok(response);
    }
}
