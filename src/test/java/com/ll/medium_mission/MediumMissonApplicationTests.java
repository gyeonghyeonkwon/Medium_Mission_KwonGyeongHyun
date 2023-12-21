package com.ll.medium_mission;

import com.ll.medium_mission.domain.home.home.Repository.QuestionRepository;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class MediumMissionApplicationTests {
    @Autowired
    private QuestionService questionService;
    private QuestionRepository questionRepository;
    private MemberService memberService;

    @Test
    void test() {


    }
}