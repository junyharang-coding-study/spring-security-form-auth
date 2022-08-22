package com.junyharang.springsecurityformauth.service.user;

import com.junyharang.springsecurityformauth.domain.dto.request.SignUpRequestDTO;

public interface UserService {
    void createUser(SignUpRequestDTO signUpRequestDTO);
}
