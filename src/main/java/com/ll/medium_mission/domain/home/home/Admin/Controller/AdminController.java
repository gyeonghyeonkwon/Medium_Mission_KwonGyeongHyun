package com.ll.medium_mission.domain.home.home.Admin.Controller;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
/**
 * 관리자 페이지
 */
public class AdminController {

private final MemberService memberService;
    @GetMapping("/member/admin")
    public String showAdmin(Model model) {
        List<MemberUser>memberUserList = this.memberService.findMemberUser();

        model.addAttribute("memberUserList" , memberUserList);

        return "domain/home/home/admin/adminPage";

    }

    @GetMapping("/member/admin/{id}")
    public String showAdminModify(@PathVariable("id") Long id, Model model) {

        MemberUser _memberUser = this.memberService.findById(id);

        model.addAttribute("memberUser", _memberUser);

        return"domain/home/home/admin/adminDetailPage";
    }

    @PostMapping("/member/admin/{id}")
    public String adminUpdate(@PathVariable("id") Long id , boolean isPaid) {

           MemberUser memberUser = memberService.findById(id);

           memberService.updateMemberIsPaid(memberUser , isPaid);

            log.info("kkhispaid :" + isPaid);

        return "redirect:/member/admin";
    }
}
