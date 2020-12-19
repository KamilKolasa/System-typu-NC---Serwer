package com.app.model.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private String accessToken;
    private String refreshToken;
    private String role;
}
