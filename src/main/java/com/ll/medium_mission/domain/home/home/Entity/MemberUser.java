package com.ll.medium_mission.domain.home.home.Entity;

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

    @Column(unique = true)
    private String username; //이름

    @Column(unique = true)
    private String nickname; //아이디

    @Column(unique = true)
    private String password; //패스워드


}

//ALTER TABLE your_table
//DROP COLUMN column_to_remove;