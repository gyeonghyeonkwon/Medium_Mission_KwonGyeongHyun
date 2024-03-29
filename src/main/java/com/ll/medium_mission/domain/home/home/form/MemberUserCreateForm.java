package com.ll.medium_mission.domain.home.home.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class MemberUserCreateForm {

    /**
     * DTO
     * 회원가입할 때 MemberController에 받아올 데이터
     */

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min = 2 , max = 4)
    private String username;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 5 , max = 20)
    private String nickname;


    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 3 , max = 20)
    private String password;

    @NotBlank(message = "비밀번호 확인란을 입력해주세요.")
    @Size(min = 3 , max = 20)
    private String passwordConfirm;
}
