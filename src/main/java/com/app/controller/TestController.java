package com.app.controller;

import com.app.model.dto.InfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/testUser")
    public InfoDto<String> testUser() {
        return InfoDto.<String>builder().data("TEST USER").build();
    }

    @GetMapping("/testAdmin")
    public InfoDto<String> testAdmin() {
        return InfoDto.<String>builder().data("TEST ADMIN").build();
    }
}
