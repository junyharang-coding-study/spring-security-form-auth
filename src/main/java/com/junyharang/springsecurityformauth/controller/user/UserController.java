package com.junyharang.springsecurityformauth.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/user")
@Controller
public class UserController {

    @GetMapping("/my-page")
    public String myPage() throws Exception {
        return "user/my-page";
    }
}
