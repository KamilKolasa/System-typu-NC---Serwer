package com.app.security;

import com.app.exception.ExceptionCode;
import com.app.exception.MyException;
import com.app.model.dto.InfoDto;
import com.app.model.dto.security.TokenDto;
import com.app.model.dto.security.AuthenticationUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

// za kazdym razem gdy domyslnie wywolasz /login obiekt tej klasy przechwyci zadanie
// logowania i bedziemy mogli wychwycic z niego username + password i dokonac autentykacji

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/my-login", "POST"));
    }

    // ta metoda zostanie automatycznie wywolana podczas proby logowania
    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {
        try {
            AuthenticationUserDto user = new ObjectMapper().readValue(request.getInputStream(), AuthenticationUserDto.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.emptyList()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.SECURITY, "attemp authentication exception");
        }
    }

    // kiedy logowanie sie powiedzie zostanie wywolana ponizsza metoda
    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        var jsonData = InfoDto.<TokenDto>builder().data(TokenGenerator.generateTokens(authResult)).build();
        response.getWriter().write(new ObjectMapper().writeValueAsString(jsonData));
        response.getWriter().flush();
        response.getWriter().close();

    }
}
