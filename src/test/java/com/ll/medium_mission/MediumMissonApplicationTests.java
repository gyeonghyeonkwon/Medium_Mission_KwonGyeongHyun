package com.ll.medium_mission;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MediumMissonApplicationTests {
    @Autowired
    private  QuestionRepository questionRepository;
    @Test
    void contextLoads() {
    MemberUser author = new MemberUser();
            author.setNickname("kyanghyang12");
            author.setPassword("1111");


        Question q1 = Question.builder()
                .title("sbb가 무엇인가요?")
                .content("sbb에 대해서 알고 싶습니다.")
                .createDate(LocalDateTime.now())
                .author(author)
                .build();

        this.questionRepository.save(q1);
    }

}
