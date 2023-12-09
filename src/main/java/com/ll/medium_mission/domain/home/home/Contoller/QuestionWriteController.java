package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Repository.QuestionRepository;
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
public class QuestionWriteController {

    private final QuestionRepository questionRepository;
    @GetMapping("/member/write")
    public String showWrite() {

        return "domain/home/home/write";
    }

    /**
     *
     * DTO 를 엔티티로 변환 하여 db 에 저장.
     */
    @PostMapping("/member/write")
    public String write(QuestionWriteForm questionWriteForm) {

        log.info(questionWriteForm.toString());

        Question question = questionWriteForm.toEntity();

        Question saved = questionRepository.save(question);

        log.info(saved.toString());

        return"";
    }
    @GetMapping("/member/write/{id}")
    public String Showdetail(@PathVariable("id") Long id) {
        log.info("id =" + id);
        return "";
    }

}
