package com.app.security;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.dto.security.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface TokenGenerator {

    // ------------------------------------------------------------------------------------
    // ------------------------------ generowanie tokena ----------------------------------
    // ------------------------------------------------------------------------------------
    static TokenDto generateTokens(Authentication authentication) {

        if (authentication == null) {
            throw new MyException(ExceptionCode.SECURITY, "generate tokens - authentication object is null");
        }

        String accessToken = generateToken(authentication, TokenType.ACCESS_TOKEN);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(generateToken(authentication, TokenType.REFRESH_TOKEN))
                .role(getRolesAsString(accessToken))
                .build();
    }

    private static String generateToken(Authentication authentication, TokenType tokenType) {

        String username = authentication.getName();

        String roles = authentication
                .getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(Collectors.joining(","));

        long expirationDateTime = SecurityConstants.EXPIRATION_TIME_ACCESS_TOKEN;

        if(tokenType.equals(TokenType.REFRESH_TOKEN)){
            roles = "ROLE_REFRESH," + roles;
            expirationDateTime = SecurityConstants.EXPIRATION_TIME_REFRESH_TOKEN;
        }

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationDateTime))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .claim("roles", roles)
                .compact();
    }

    private static String generateAccessTokenFromRefreshToken(String username, String roles) {

        long expirationDateTime = SecurityConstants.EXPIRATION_TIME_ACCESS_TOKEN;

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expirationDateTime))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .claim("roles", roles)
                .compact();
    }

    // ------------------------------------------------------------------------------------
    // ------------------------------- parsowanie tokena ----------------------------------
    // ------------------------------------------------------------------------------------

    static UsernamePasswordAuthenticationToken parseAccessToken(String accessToken) {

        if (accessToken == null) {
            throw new MyException(ExceptionCode.SECURITY, "parse access token - access token is null");
        }

        if (!isTokenWithCorrectPrefix(accessToken)) {
            throw new MyException(ExceptionCode.SECURITY, "access token doesn't start with correct prefix");
        }

        if (!isTokenValid(accessToken)) {
            throw new MyException(ExceptionCode.SECURITY, "access token is not valid");
        }

        String username = getUsername(accessToken);
        var authorities = getRoles(accessToken);
        return new UsernamePasswordAuthenticationToken(username, null, authorities);

    }

    static TokenDto generateTokensFromRefreshToken(String refreshToken) {

        if (refreshToken == null) {
            throw new MyException(ExceptionCode.SECURITY, "parse access token - access token is null");
        }

        if (!isTokenValid(refreshToken)) {
            throw new MyException(ExceptionCode.SECURITY, "access token is not valid");
        }

        var authorities = getRolesAsString(refreshToken);

        if (!authorities.contains("ROLE_REFRESH")) {
            throw new MyException(ExceptionCode.SECURITY, "token is not refresh token");
        }

        String username = getUsername(refreshToken);

        String accessToken = generateAccessTokenFromRefreshToken(username, authorities);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private static boolean isTokenWithCorrectPrefix(String token) {
        return token.startsWith(SecurityConstants.TOKEN_PREFIX);
    }

    private static boolean isTokenValid(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date());
    }

    private static Claims getClaims(String token) {
        return Jwts
                .parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getBody();
    }

    private static String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    private static String getRolesAsString(String token) {
        return getClaims(token).get("roles").toString();
    }

    private static List<GrantedAuthority> getRoles(String token) {
        return Arrays
                .stream(getClaims(token).get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
