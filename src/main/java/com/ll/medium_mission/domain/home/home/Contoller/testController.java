package com.ll.medium_mission.domain.home.home.Contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 테스트 컨트롤러
 */
@Controller
public class testController {


    @GetMapping("/member/test")
    public String test() {




        return "/domain/home/home/test/test" ;
    }

}
