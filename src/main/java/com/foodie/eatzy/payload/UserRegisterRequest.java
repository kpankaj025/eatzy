package com.foodie.eatzy.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "user name is required")
    private String name;
    @Email(message = "valid email is required")
    private String email;
    @NotBlank(message = "message required")
    @Size(min = 3, max = 15, message = "min 3 and max 15")
    private String password;
    @NotBlank(message = "required")
    private String confirmPassword;

}
