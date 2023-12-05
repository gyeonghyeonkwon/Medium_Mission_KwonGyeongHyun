package com.ll.medium_misson.domain.home.home.Contoller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/")
    public String showMain() {
        return "domain/home/home/main";
    }

}
