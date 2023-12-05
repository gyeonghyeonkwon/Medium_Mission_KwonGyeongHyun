package com.ll.medium_misson.domain.home.home.MemberUser;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MemberUser {

    /**
     * 회원가입유저 테이블
     */
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column(unique = true)
    private String password;

    @Column(unique = true)
    private String email ;



}
