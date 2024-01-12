package com.ll.medium_mission.domain.home.home.Admin.Controller;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.global.Rq;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
@ToString
/**
 * 관리자 페이지
 */
public class AdminController {

private final MemberService memberService;
private final Rq rq;

    @GetMapping("/member/admin")
    public String showAdmin(Model model , @RequestParam(value = "page" , defaultValue = "1") int page) {
        Page<MemberUser> memberUserList = this.memberService.getMemberUserList(page);
        int nowPage = memberUserList.getPageable().getPageNumber() +1 ;

        model.addAttribute("nowPage" , nowPage);
        model.addAttribute("memberUserList" , memberUserList);

        return "domain/home/home/admin/adminPage";

    }

    @GetMapping("/member/admin/{id}")
    public String showAdminModify(@PathVariable("id") Long id, Model model) {

        MemberUser _memberUser = this.memberService.findById(id);

        model.addAttribute("memberUser", _memberUser);

        return"domain/home/home/admin/adminDetailPage";
    }

    /**
     * 회원 엔티티 isPaid 의 정보를 수정
     */
    @PostMapping("/member/admin/{id}")
    public String adminUpdate(@PathVariable("id") Long id , boolean isPaid) {

           MemberUser memberUser = memberService.findById(id);

           memberService.updateMemberIsPaid(memberUser , isPaid);

            log.info("kkhispaid :" + isPaid);


        return rq.redirect("/member/admin" , "%s님 권한 이 수정 되었습니다.".formatted(memberUser.getNickname()));
    }
}
