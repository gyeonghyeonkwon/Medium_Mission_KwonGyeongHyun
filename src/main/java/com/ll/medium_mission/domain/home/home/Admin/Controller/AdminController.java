package com.ll.medium_mission.domain.home.home.Admin.Controller;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Service.MemberService;
import com.ll.medium_mission.domain.home.home.form.MemberUserCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
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

        MemberUserCreateForm memberUserCreateForm = new MemberUserCreateForm();

        memberUserCreateForm.setPaid(_memberUser.isPaid());


        model.addAttribute("memberUser", _memberUser);
        model.addAttribute("memberUserCreateForm", memberUserCreateForm); // 추가

        return"domain/home/home/admin/adminDetailPage";
    }

    @PostMapping("/member/admin/{id}")
    public String adminUpdate(@PathVariable("id") Long id , @RequestParam(name = "isPaid", required = false) boolean isPaid) {

           MemberUser memberUser = memberService.findById(id);

           memberService.updateMemberIsPaid(memberUser , isPaid);

            log.info("kkhispaid :" + isPaid);

        // 업데이트 후 리다이렉트 또는 다른 처리를 수행할 수 있음
        return "redirect:/member/admin";
    }
}
