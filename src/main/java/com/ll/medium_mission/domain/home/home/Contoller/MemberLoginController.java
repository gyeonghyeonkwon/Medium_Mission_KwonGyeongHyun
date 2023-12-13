package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MemberLoginController {
  private final  MemberService memberService ;
    /**
     * 로그인 페이지
     */
    @GetMapping("/")
    public String loginRedirect() {

        return "redirect:/member/login";
    }

    /**
     *
     *  로그인 되어 있지 않는 사용자 만이 접속 가능
     *
     *  현재 서버에는 강제로 로그인하게 되어있다
     *  로그아웃 되면 login 화면으로 넘어갈수없다.
     */
    @PreAuthorize("isAnonymous()")
    @GetMapping("/member/login")
    public String loginPage() {

        return "domain/home/home/login";
    }


}
