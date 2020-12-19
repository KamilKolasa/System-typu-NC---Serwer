package com.app.security;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)  {

        try {
            String accessToken = request.getHeader(SecurityConstants.HEADER_STRING_ACCESS_TOKEN);
            if ( accessToken == null || !accessToken.startsWith(SecurityConstants.TOKEN_PREFIX) ) {
                chain.doFilter(request, response);
                return;
            }
            UsernamePasswordAuthenticationToken authenticationToken = TokenGenerator.parseAccessToken(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.SECURITY, "do filter internal exception");
        }
    }
}
