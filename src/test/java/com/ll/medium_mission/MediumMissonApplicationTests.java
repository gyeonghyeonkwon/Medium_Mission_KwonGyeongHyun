package com.ll.medium_mission;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MediumMissionApplicationTests {
    @Autowired
    private QuestionService questionService;
    @Test
    void test() {

        for (int i=1; i <= 30; i++) {
            String title = "테스트 제목 : [%03d]%n".formatted(i);

            String content = "테스트 내용";

            MemberUser author = new MemberUser();
            author.setNickname("test"+i);
        this.questionService.write(title , content , author );
    }

}
}