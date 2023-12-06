package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.form.MemberUserCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Member 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원가입 페이지
     */
    @GetMapping("/join")
    public String joinPage(MemberUserCreateForm memberUserCreateForm) {

        return "domain/home/home/join";
    }

    /**
     * 회원 가입 페이지 처리
     */

    @PostMapping("/join")
    public String joinPage(@Valid MemberUserCreateForm memberUserCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/join";
        }


        if (!memberUserCreateForm.getPassword().equals(memberUserCreateForm.getPasswordConfirm())) {
            bindingResult.rejectValue("PasswordConfirm", "passwordInCorrect",
                    "입력하신 비밀번호가 일치하지 않습니다.");

            return "domain/home/home/join";
        }
        memberService.create(memberUserCreateForm.getUsername() , memberUserCreateForm.getNickname(), memberUserCreateForm.getPassword());

        return "redirect:domain/home/home/list";
    }
}



