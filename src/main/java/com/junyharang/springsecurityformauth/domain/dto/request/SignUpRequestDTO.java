package com.junyharang.springsecurityformauth.domain.dto.request;

import lombok.Data;

@Data
public class SignUpRequestDTO {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

}
