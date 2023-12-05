package com.ll.medium_misson.domain.home.home.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateForm {

    //회원 가입
    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 3 , max = 20)
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "비밀번호 확인란을 입력해주세요.")
    private String passwordConfirm;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email
    private String email;
}
