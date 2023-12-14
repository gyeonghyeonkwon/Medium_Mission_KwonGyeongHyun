package com.ll.medium_mission.domain.home.home.Contoller;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardListController {

    private final QuestionService questionService;
    private final MemberService memberService;
    @GetMapping("/member/list")
    public String showList(Model model ,MemberUser memberUser) {
        /**
         * 게시글 리스트
         * 질문 DB 테이블 에 저장되어있는 데이터를 조회 하여 뷰로 전달.
         *
         * 엔티티 id 값을 엔티티의 이름을 알아내어 뷰로 전달
         *
         */
        List<Question> questionList = this.questionService.getList();

        String loginUser = memberUser.getUsername();

        model.addAttribute("questionList" , questionList );

        model.addAttribute("loginUser" , loginUser );
        return "/domain/home/home/list";
    }

    /**
     * 내가 작성 한 글 불러오기
     * 로그인 사용자 의 게시글을 저장하여 타임리프에 전달.
     *  자신의 계정이 아니면 게시글을 볼수 없다.
     */

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/myList")
    public String showMyList(Model model , Principal principal) {

        String username = principal.getName();

        List<Question> myList = this.questionService.getUserMyList(username);

        model.addAttribute("myList" , myList);

        return "/domain/home/home/myList";
    }

    /**
     * 회원이름으로 조회
     */
    @GetMapping("/member/{username}")
    public String nameSearch(@PathVariable("username") String username , Model model) {

        List<Question> myList = this.questionService.getUserMyList(username);
        model.addAttribute("myList" , myList);

        return "/domain/home/home/myList";
    }

    /**
     *
     * @회원 아이디 게시글 상세보기
     */
    @GetMapping("/member/{username}/{id}")
    public String nameIdSearch(@PathVariable("username") String username , @PathVariable("id") Long id , Model model){

        Question question = this.questionService.getQuestion(id);

        model.addAttribute("question", question);

        return  "/domain/home/home/detailWrite";
    }
}
