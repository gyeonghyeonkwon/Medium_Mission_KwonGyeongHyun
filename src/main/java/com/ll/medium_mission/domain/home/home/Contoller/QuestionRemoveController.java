package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class QuestionRemoveController {

    private  final QuestionService questionService;

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        Question question = this.questionService.getQuestion(id);

        this.questionService.delete(question.getId());

        return "redirect:/member/list";

    }
}
