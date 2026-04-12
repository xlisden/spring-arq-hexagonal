package com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.authentication;

import com.davinchicoder.spring_data_jpa_cero_a_experto.common.infrastructure.service.JwtService;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.domain.port.AuthenticationPort;
import com.davinchicoder.spring_data_jpa_cero_a_experto.user.infraestructure.database.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationImpl implements AuthenticationPort {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String authenticate(String username, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)); // password sin hashear
        UserEntity entity = (UserEntity) authenticate.getPrincipal();
        String token = jwtService.genToken(entity);
        return token;
    }
}
