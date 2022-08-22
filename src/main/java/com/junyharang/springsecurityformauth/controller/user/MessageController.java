package com.junyharang.springsecurityformauth.controller.user;

import com.junyharang.springsecurityformauth.constant.ServiceURIManagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ServiceURIManagement.NOW_VERSION + "/user")
@Controller
public class MessageController {

    @GetMapping("/messages")
    public String myMessages() throws Exception {
        return "/api/v1/user/messages";
    }
}
