package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import com.ll.medium_mission.domain.home.home.form.QuestionWriteForm;
import com.ll.medium_mission.global.Rq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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
    private final Rq rq;

    @GetMapping("/member/write")
    public String showWrite(QuestionWriteForm  QuestionWriteForm) {

        return "domain/home/home/write";
    }

    /**
     * DTO를 받아 db에 저장
     *
     *
     */
    @PostMapping("/member/write")

    public String write(@Valid QuestionWriteForm questionWriteForm, Principal principal) {

        MemberUser memberUser = this.memberService.getUser(principal.getName());

        this.questionService.write(questionWriteForm.getTitle(), questionWriteForm.getContent(), memberUser , questionWriteForm.getIsPublished() , questionWriteForm.getIsPaid());
        return rq.redirect("/member/list","게시물이 등록되었습니다.");
    }

    /**
     *
     *URL 에 QUESTION ID 값을 매핑 시켜 해당하는 글을 보게한다.
     */
    @GetMapping("/member/write/{id}")
    public String showWriteDetail(Model model, @PathVariable("id") Long id , Authentication authentication) {
        // 글 목록이 없으면 예외
        Question question = this.questionService.getQuestion(id);

        model.addAttribute("question", question);

    try {

        if (question.getIsPaid() && !memberService.isPaidMember(authentication)) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "유료 회원 전용 글 입니다. ");
                model.addAttribute("isPaidMsg" , "이 글은 유료 멤버십 전용 입니다. ")    ;
        }
    }
        catch (NullPointerException e){
            return "domain/home/home/detailWrite";

        }

            return "domain/home/home/detailWrite";
    }


}
