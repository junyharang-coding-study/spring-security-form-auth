package com.junyharang.springsecurityformauth.controller.user;

import com.junyharang.springsecurityformauth.constant.ServiceURIManagement;
import com.junyharang.springsecurityformauth.domain.dto.request.SignUpRequestDTO;
import com.junyharang.springsecurityformauth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping(ServiceURIManagement.NOW_VERSION + "/user/my-page")
    public String myPage() throws Exception {
        return "/api/v1/user/my-page";
    }

    @GetMapping("/signup")
    public String createUser() {
        return "/signup";
    }

    @PostMapping("/signup")
    public String createUser(SignUpRequestDTO signUpRequestDTO) {
        userService.createUser(signUpRequestDTO);

        return "redirect:/";
    }
}
