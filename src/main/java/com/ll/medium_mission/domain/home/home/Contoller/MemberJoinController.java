package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.form.MemberUserCreateForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  회원가입 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberJoinController {

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
    public String joinPage(@Valid MemberUserCreateForm memberUserCreateForm, Model model) {
        /**
         * 입력한 비밀번호가 서로 일치 하지 않는다면 예외 발생
         * 문제없다면 DB 저장
         * 로그인 성공 시 글 목록 페이지로 이동
         */

        try {
            // 비밀번호 일치 여부 확인
            if (!memberUserCreateForm.getPassword().equals(memberUserCreateForm.getPasswordConfirm())) {
                throw new IllegalArgumentException("입력하신 비밀번호가 일치하지 않습니다.");
            }
            // 사용자 닉네임 중복 되면 실행
             this.memberService.findByNickname(memberUserCreateForm);

            //아이디가 중복 되지 않는다면 아이디생성
            this.memberService.create(memberUserCreateForm.getUsername(), memberUserCreateForm.getNickname(), memberUserCreateForm.getPassword());

            return "redirect:/member/login";
        }
        //  에러를 model 에 담아 타임 리프 로 전달
        catch (IllegalArgumentException e) {

            model.addAttribute("error" , e.getMessage());
            return "domain/home/home/join";
        }
    }
}



