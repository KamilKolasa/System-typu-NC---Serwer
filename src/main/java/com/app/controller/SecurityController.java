package com.app.controller;

import com.app.service.model.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {

    private final UserService userService;

    @GetMapping("/activateUser")
    public String activateUser(@RequestParam String token){
        userService.activateUser(token);
        return "redirect:http://localhost:8082/";
    }
}
