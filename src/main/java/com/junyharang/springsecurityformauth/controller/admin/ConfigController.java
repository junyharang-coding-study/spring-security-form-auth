package com.junyharang.springsecurityformauth.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/admin")
@Controller
public class ConfigController {

    @GetMapping("/config")
    public String config() {
        return "admin/config";
    }
}
