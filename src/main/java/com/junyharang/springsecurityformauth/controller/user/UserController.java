package com.junyharang.springsecurityformauth.controller.user;

import com.junyharang.springsecurityformauth.constant.ServiceURIManagement;
import com.junyharang.springsecurityformauth.domain.dto.request.SignUpRequestDTO;
import com.junyharang.springsecurityformauth.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @GetMapping("/signin")
    public String signIn(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception,
            Model model) {

        userService.signIn(error, exception, model);

        return "/signin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);

        return "redirect:/";
    }

    @GetMapping("/denied")
    public String accessDenied(
            @RequestParam(value = "exception", required = false) String exception,
            Model model) {

        userService.accessDenied(exception, model);

        return "/error/denied";
    }
}
