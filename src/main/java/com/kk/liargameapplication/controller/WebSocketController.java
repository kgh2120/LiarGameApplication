package com.kk.liargameapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {

    @GetMapping("/")
    public String chat() {
        return "chat";
    }
}
