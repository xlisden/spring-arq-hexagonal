package com.davinchicoder.spring_data_jpa_cero_a_experto.common.infrastructure.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String JWT_SECRET = "";
    private static final long TIME_EXP = 1000 * 60 * 30 * 24;
    private static final long REFRESH_WINDOW = 1000 * 60 * 30 * 24 * 7; // renovado hasta 7 dias de generado

    public String genToken(UserDetails userDetails) {
        Map<String, Object> claims = Map.of(
                "authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList() // solo para obtener la autoridad en texto
        );
        return genToken(claims, userDetails.getUsername());
    }

    public String genToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis())) // como created at
                .setExpiration(new Date(System.currentTimeMillis() + TIME_EXP))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact()
                ;
    }

    private Key getSignKey() {
        byte[] keysBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keysBytes);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token) //con parseClaimsJwt daba error
                    .getBody();
//        } catch (ExpiredJwtException e) {
//            return e.getClaims(); // si expira igual trae los claims
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token or mal formed", e);
        }
        return claims;
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsMapper) {
        Claims claims = getClaimsFromToken(token);
        return claimsMapper.apply(claims);
    }

    public String getUsername(String token) {
//        return getClaimsFromToken(token).getSubject(); //no se usa porque trae todo?
        return getClaim(token, Claims::getSubject);
    }

    private Date getExpirationDate(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    public boolean canBeTokenRenewed(String token) {
        return getExpirationDate(token).before(new Date(System.currentTimeMillis() + REFRESH_WINDOW));
    }

    public String renewToken(String token, UserDetails userDetails) {
        if (!canBeTokenRenewed(token)) {
            throw new RuntimeException("Token cannot be renewed");
        }
        return genToken(userDetails);
    }

}
