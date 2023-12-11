package com.ll.medium_mission.domain.home.home.Contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberLoginController {

    /**
     * 로그인 페이지
     */
    @GetMapping("/")
    public String loginRedirect() {

        return "redirect:/member/login";
    }
    @GetMapping("/member/login")
    public String loginPage() {

        return "domain/home/home/login";
    }

}
