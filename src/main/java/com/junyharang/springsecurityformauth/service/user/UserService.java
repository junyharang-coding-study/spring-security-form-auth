package com.junyharang.springsecurityformauth.service.user;

import com.junyharang.springsecurityformauth.domain.dto.request.SignUpRequestDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    void createUser(SignUpRequestDTO signUpRequestDTO);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void signIn(String error, String exception, Model model);
}
