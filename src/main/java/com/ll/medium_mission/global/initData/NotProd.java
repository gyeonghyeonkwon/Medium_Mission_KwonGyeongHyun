package com.ll.medium_mission.global.initData;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 더미 데이터
 */
@Profile("!prod")
@Configuration
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(MemberService memberService , QuestionService questionService ) {

        return args -> {
            MemberUser memberAdmin =  memberService.create("admin" , "admin" , "111"); //관리자 계정
            memberService.updateMemberIsPaid(memberAdmin , true); //유료 권한
            for (int i=1; i <= 100; i++) {
                String nickname = "user" + i ;
                String username = "test" +i;
                String password = "111";
                MemberUser memberUser = memberService.create(username , nickname , password);
                memberService.updateMemberIsPaid(memberUser  , true); //유료 회원
                questionService.write("테스트제목" +i , "테스트내용" +i ,memberUser , false , true) ; //유료 글

            }

        };

    }

}
