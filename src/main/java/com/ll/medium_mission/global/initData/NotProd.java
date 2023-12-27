package com.ll.medium_mission.global.initData;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 더미 데이터
 */
@Profile("!prod")
@Configuration
public class NotProd {

//    @Bean
//    public ApplicationRunner initNotProd(MemberService memberService , QuestionService questionService) {
//
//        return args -> {
//
//            for (int i=1; i <= 100; i++) {
//                String nickname = "user" + i ;
//                String password = "111";
//                MemberUser memberUser = memberService.create("권경현", nickname , password);
//                questionService.write("테스트제목" +i , "테스트내용" +i ,memberUser , false) ;
//            }
//        };
//
//    }

}
