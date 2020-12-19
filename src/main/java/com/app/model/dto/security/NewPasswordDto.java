package com.app.model.dto.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewPasswordDto {
    private String email;
    private String newPassword;
    private String confirmNewPassword;
}
