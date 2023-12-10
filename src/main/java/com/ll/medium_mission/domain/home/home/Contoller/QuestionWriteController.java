package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import com.ll.medium_mission.domain.home.home.form.QuestionWriteForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@ToString
@Slf4j
public class QuestionWriteController {

    private final QuestionService questionService;
    private final MemberService memberService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/write")
    public String showWrite(QuestionWriteForm  QuestionWriteForm) {

        return "domain/home/home/write";
    }

    /**
     *
     *
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/member/write")

    public String write(@Valid QuestionWriteForm questionWriteForm, Principal principal) {

        MemberUser memberUser = this.memberService.getUser(principal.getName());

        this.questionService.write(questionWriteForm.getTitle(), questionWriteForm.getContent(), memberUser);

        return "redirect:/member/list";
    }

    @GetMapping("/member/write/{id}")
    public String writeDetail(Model model, @PathVariable("id") Long id) {

        return "domain/home/home/detail";
    }
}
