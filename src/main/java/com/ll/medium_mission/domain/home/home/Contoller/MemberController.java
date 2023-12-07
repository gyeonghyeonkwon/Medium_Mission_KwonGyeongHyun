package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.form.MemberUserCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  Member 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원가입 페이지
     */
    @GetMapping("/join")
    public String showJoinPage(MemberUserCreateForm memberUserCreateForm) {

        return "domain/home/home/join";
    }

    /**
     * 회원 가입 페이지 처리
     *
     */

    @PostMapping("/join")
    public String joinPage(@Valid MemberUserCreateForm memberUserCreateForm) {
        /**
         * 입력한 비밀번호가 서로 일치 하지 않는다면 예외 발생
         * 문제없다면 DB 저장
         * 로그인 성공 시 글 목록 페이지로 이동
         */

        try {
            // 비밀번호 일치 여부 확인
            if (!memberUserCreateForm.getPassword().equals(memberUserCreateForm.getPasswordConfirm())) {
                throw new RuntimeException("입력하신 비밀번호가 일치하지 않습니다.");
            }
            memberService.create(memberUserCreateForm.getUsername(), memberUserCreateForm.getNickname(), memberUserCreateForm.getPassword());

            return "domain/home/home/list";

        }
        catch (DataIntegrityViolationException e) {

            return "domain/home/home/list";
        }
    }
}



