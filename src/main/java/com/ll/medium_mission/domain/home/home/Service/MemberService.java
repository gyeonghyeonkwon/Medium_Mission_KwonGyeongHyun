package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

        user.setUsername(username); //이름

        user.setNickname(nickname); //아이디

        user.setPassword(passwordEncoder.encode(password));

        this.memberRepository.save(user);

        return user;

    }
    @Transactional(readOnly = true)
    public Optional<MemberUser> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

}
