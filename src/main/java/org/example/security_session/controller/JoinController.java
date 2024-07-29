package org.example.security_session.controller;

import lombok.RequiredArgsConstructor;
import org.example.security_session.dto.JoinDTO;
import org.example.security_session.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO request) {

        System.out.println("request.getUsername() = " + request.getUsername());

        joinService.joinProcess(request);

        return "redirect:/login";
    }
}
