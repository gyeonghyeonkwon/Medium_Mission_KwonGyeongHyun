package com.ll.medium_mission.domain.home.home.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberListController {

    @GetMapping("/member/list")
    public String showList() {

        return "/domain/home/home/list";
    }
}
