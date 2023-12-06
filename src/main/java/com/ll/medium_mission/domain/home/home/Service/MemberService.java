package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /**
     *  memberuser DB 저장
     *  비밀번호 암호화하여 db 저장
     */
    public MemberUser create (String username , String nickname ,String password) {

        MemberUser user = new MemberUser();

        user.setUsername(username);

        user.setNickname(nickname);

        user.setPassword(passwordEncoder.encode(password));

        this.memberRepository.save(user);

        return user;

    }

}