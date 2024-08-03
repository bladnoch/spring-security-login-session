package org.example.security_session.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainP(Model model) {

        /*
            로그인 진해 뒤 사용자 정보는 SecurityContextHoder에 의해 세션에 관리된다.
         */

        // 로그인한 사용자의 아이디 가져오기
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        /**
         * 로그인한 사용자의 롤 값 가져오기
         * 한명의 사용자가 여러개의 role을 가질 수 있기 때문에 collection을 통해서 role 값을 가져온다
          */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();


        model.addAttribute("id", id);
        model.addAttribute("role", role);



        return "main";
    }
}
