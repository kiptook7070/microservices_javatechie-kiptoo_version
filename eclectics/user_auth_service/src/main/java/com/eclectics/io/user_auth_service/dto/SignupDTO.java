package com.eclectics.io.user_auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignupDTO extends UserDTO {
    @NotBlank
    @NotNull
    private String password;
}
