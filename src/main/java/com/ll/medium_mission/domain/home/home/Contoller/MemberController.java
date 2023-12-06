package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.form.MemberCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 회원가입 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {

        return "domain/home/home/login";
    }


    /**
     * 회원 가입 페이지 처리
     */

    @PostMapping("/join")
    public String loginPage(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {

        if (!memberCreateForm.getPassword().equals(memberCreateForm.getPasswordConfirm())) {
            bindingResult.rejectValue("PasswordConfirm", "passwordInCorrect",
                    "입력하신 비밀번호가 일치하지 않습니다.");

            return "domain/home/home/join";
        }
        return "redirect:/";
    }
}



