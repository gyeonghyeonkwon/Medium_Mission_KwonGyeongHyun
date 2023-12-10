package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Repository.MemberRepository;
import com.ll.medium_mission.domain.home.home.form.MemberUserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
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

    /**
     * db에서 저장된 아이디를 찾아
     * 중복된 아이디가 있으면 예외를 발생킨다.
     */

    public void findByNickname (MemberUserCreateForm memberUserCreateForm) {

        Optional<MemberUser> memberUser = memberRepository.findByNickname(memberUserCreateForm.getNickname());

        if (memberUser.isPresent()) {
            throw new IllegalArgumentException("아이디가 존재합니다.");
        }

    }

    public MemberUser getUser(String nickname) {

        Optional<MemberUser> findUser = memberRepository.findByNickname(nickname);

        if (findUser.isPresent()) {
            return findUser.get();
        }
        throw new IllegalArgumentException("아이디가 존재합니다.");
    }

}



