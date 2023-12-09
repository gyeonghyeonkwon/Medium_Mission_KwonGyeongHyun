package com.ll.medium_mission.domain.home.home.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberWriteController {

    @GetMapping("/member/write")
    public String showWrite() {



        return "domain/home/home/write";
    }
}
