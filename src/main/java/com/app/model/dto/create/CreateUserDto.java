package com.app.model.dto.create;

import com.app.model.enums.Role;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDto {

    private String username;
    private String password;
    private String passwordConfirmation;
    private String email;
    private Boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;
}
