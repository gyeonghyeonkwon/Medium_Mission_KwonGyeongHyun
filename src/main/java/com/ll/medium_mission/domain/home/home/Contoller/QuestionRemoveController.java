package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import com.ll.medium_mission.global.Rq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class QuestionRemoveController {

    private  final QuestionService questionService;
    private final Rq rq;

    /**
     *
     *  로그인 한 계정으로 만 자신의 글 삭제가가능하다
     * */
    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id , Principal principal) {

        Question question = this.questionService.getQuestion(id);

        if (!question.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question.getId());
        //삭제 메세지
        return rq.redirect("/member/list","%d번 게시물이 삭제가 완료되었습니다".formatted(question.getId()));

    }
}
