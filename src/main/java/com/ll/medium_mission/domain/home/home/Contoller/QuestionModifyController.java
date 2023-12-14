package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import com.ll.medium_mission.domain.home.home.form.QuestionWriteForm;
import com.ll.medium_mission.global.Rq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@ToString
@Slf4j
public class QuestionModifyController {

    private final QuestionService questionService;
    private final Rq rq;

    /**
     * 게시판 아이디 를 조회 하여  제목 ,내용 을 불러 옴
     */
    @GetMapping("/member/modify/{id}")
    public String showModify(@PathVariable("id") Long id, QuestionWriteForm questionWriteForm) {

        Question question = this.questionService.getQuestion(id);

        questionWriteForm.setTitle(question.getTitle());

        questionWriteForm.setContent(question.getContent());
        return "domain/home/home/modify";

    }

    /**
     * 로그인 된 사용자만 이 수정이 가능하다
     *
     */
    @PostMapping("/member/modify/{id}")
    public String update(@PathVariable("id") Long id, @Valid QuestionWriteForm questionWriteForm, Principal principal) {

        Question question = this.questionService.getQuestion(id);

        if (!question.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정권한이 없습니다.");
        }
            // 제목 , 내용 순으로 불러 와야 한다.
            questionService.modifySave(question, questionWriteForm.getTitle(), questionWriteForm.getContent());

            return rq.redirect("/member/write/%s" , "test").formatted(id);
        }


}
