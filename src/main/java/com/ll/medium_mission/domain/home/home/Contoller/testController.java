package com.ll.medium_mission.domain.home.home.Contoller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/**
 * 테스트 컨트롤러
 */
@Controller
public class testController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/member/test")
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 사용자가 인증되지 않은 경우 리다이렉트
        response.sendRedirect(request.getContextPath() + "/member/login");
    }
}
