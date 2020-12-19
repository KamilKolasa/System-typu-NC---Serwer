package com.app.controller;

import com.app.model.dto.InfoDto;
import com.app.model.dto.security.ForgetPasswordDto;
import com.app.model.dto.security.NewPasswordDto;
import com.app.model.dto.security.TokenDto;
import com.app.model.dto.create.CreateUserDto;
import com.app.security.TokenGenerator;
import com.app.service.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityRestController {

    private final UserService userService;

    @PostMapping("/registration")
    public InfoDto<String> registerNewUser(@RequestBody CreateUserDto createUserDto, HttpServletRequest request) {
        var registeredUser = userService.registerUser(createUserDto, request);
        return InfoDto.<String>builder().data("user " + registeredUser.getUsername() + " registered").build();
    }

    @PostMapping("/refreshToken")
    public InfoDto<TokenDto> refreshToken(@RequestParam String token) {
        return InfoDto.<TokenDto>builder().data(TokenGenerator.generateTokensFromRefreshToken(token)).build();
    }

    @PostMapping("/activateUser")
    public InfoDto<String> activateUser(@RequestParam String token) {
        userService.activateUser(token);
        return InfoDto.<String>builder().data("user activation successful").build();
    }

    @PostMapping("/forgetPassword")
    public InfoDto<String> registerNewUser(@RequestBody ForgetPasswordDto forgetPasswordDto, HttpServletRequest request) {
        userService.generateTokenResetPassword(forgetPasswordDto, request);
        return InfoDto.<String>builder().data("Password reset link has been sent to email: " + forgetPasswordDto.getEmail()).build();
    }
}
