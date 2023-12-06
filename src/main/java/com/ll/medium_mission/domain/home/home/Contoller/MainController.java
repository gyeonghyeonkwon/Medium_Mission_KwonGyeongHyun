package com.ll.medium_mission.domain.home.home.Contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String loginRedirect() {

        return "redirect:/member/login";
    }

}
