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

@Controller
@RequiredArgsConstructor
@ToString
@Slf4j
public class QuestionModify {

    private final QuestionService questionService;

    @GetMapping("/member/modify/{id}")
    public String modify(@PathVariable("id") Long id ,  QuestionWriteForm questionWriteForm){

        Question question = this.questionService.getQuestion(id);



         questionWriteForm.setTitle(question.getTitle());
        questionWriteForm.setContent(question.getContent());
        log.info(questionWriteForm.getTitle());
        return "domain/home/home/modify";
    }

    
}
