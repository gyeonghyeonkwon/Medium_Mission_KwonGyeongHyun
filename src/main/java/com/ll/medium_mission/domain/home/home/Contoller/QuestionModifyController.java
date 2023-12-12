package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import com.ll.medium_mission.domain.home.home.form.QuestionWriteForm;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@ToString
@Slf4j
public class QuestionModifyController {

    private final QuestionService questionService;

    /**
     * 게시판 아이디 를 조회 하여  제목 ,내용 을 불러 옴
     *
     */
    @GetMapping("/member/modify/{id}")
    public String showModify(@PathVariable("id") Long id, QuestionWriteForm questionWriteForm) {

        Question question = this.questionService.getQuestion(id);

        questionWriteForm.setTitle(question.getTitle());

        questionWriteForm.setContent(question.getContent());
        return "domain/home/home/modify";

    }

    @PostMapping("/member/modify/{id}")
    public String update(@PathVariable("id") Long id, QuestionWriteForm questionWriteForm ) {

        Question question = this.questionService.getQuestion(id);

        questionService.modifySave(question , questionWriteForm.getContent() , questionWriteForm.getTitle());


        return String.format("redirect:/member/write/%s", id);
    }



}
