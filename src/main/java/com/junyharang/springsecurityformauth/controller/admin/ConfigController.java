package com.junyharang.springsecurityformauth.controller.admin;

import com.junyharang.springsecurityformauth.constant.ServiceURIManagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ServiceURIManagement.NOW_VERSION + "/admin")
@Controller
public class ConfigController {

    @GetMapping("/config")
    public String config() {
        return "/api/v1/admin/config";
    }
}
