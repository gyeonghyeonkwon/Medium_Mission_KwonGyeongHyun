package com.ll.medium_mission.domain.home.home.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private String username; //이름

    @Column(unique = true)
    private String nickname; //아이디

    @Column
    private String password; //패스워드

    private boolean isPaid; //유료회원 여부

    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));

        if (isPaid()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PAID"));
        }

        if ("admin".equals(nickname)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authorities;
    }
}

