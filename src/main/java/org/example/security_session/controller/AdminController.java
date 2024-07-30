package org.example.security_session.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminP() {
        System.out.println("AdminController.adminP");
        return "admin";
    }
}
