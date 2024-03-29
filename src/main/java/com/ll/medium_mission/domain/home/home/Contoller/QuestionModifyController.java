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
import org.springframework.ui.Model;
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
     * 게시판 아이디 를 조회 하여  제목 ,내용 , 체크 박스 를 불러 옴
     * 데이터 베이스에 저장 되어 있는 체크 여부를 불러옴
     */
    @GetMapping("/member/modify/{id}")
    public String showModify(@PathVariable("id") Long id, QuestionWriteForm questionWriteForm , Model model) {

        Question question = this.questionService.getQuestion(id);

        questionWriteForm.setTitle(question.getTitle());

        questionWriteForm.setContent(question.getContent());

        questionWriteForm.setIsPublished(question.getIsPublished()); // 공개 여부

        questionWriteForm.setIsPaid(question.getIsPaid()); //유료 여부

        model.addAttribute("questionWriteForm" , questionWriteForm);
        return "domain/home/home/modify";

    }

    /**
     * 게시글 수정 된 내용을 저장
     * 로그인 된 사용자만 이 수정이 가능하다
     *
     */
    @PostMapping("/member/modify/{id}")
    public String update(@PathVariable("id") Long id, @Valid QuestionWriteForm questionWriteForm, Principal principal) {

        Question question = this.questionService.getQuestion(id);

        if (!question.getAuthor().getNickname().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정권한이 없습니다.");
        }
        // 제목 , 내용 , 체크 박스 순으로 불러 와야 한다.
        questionService.modifySave(question, questionWriteForm.getTitle(), questionWriteForm.getContent() , questionWriteForm.getIsPublished() , questionWriteForm.getIsPaid());

        return rq.redirect("/member/write/%s".formatted(id) , "%s 번 게시물이 수정되었습니다 ".formatted(question.getId()));
    }

}
