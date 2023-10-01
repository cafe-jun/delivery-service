package org.delivery.api.domain.user.controller.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank
    private String namel;
    @NotBlank
    private String email;
    @NotBlank
    private String address;
    @NotBlank
    private String password;
}
