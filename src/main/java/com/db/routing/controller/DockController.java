package com.db.routing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DockController {
    @GetMapping("/")
    public String openSwagger() {
        return "redirect:swagger-ui/index.html";
    }
}
