package com.ll.medium_misson.domain.home.home.Contoller;

import com.ll.medium_misson.domain.home.home.Service.MemberService;
import com.ll.medium_misson.domain.home.home.form.MemberCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 회원가입 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String LoginPage(@Valid MemberCreateForm memberCreateForm ,
                            BindingResult bindingResult) {

    }

}
