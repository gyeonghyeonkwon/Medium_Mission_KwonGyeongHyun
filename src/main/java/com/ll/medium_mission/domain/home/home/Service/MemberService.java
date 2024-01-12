package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Repository.MemberRepository;
import com.ll.medium_mission.domain.home.home.form.MemberUserCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    /**
     *  memberuser DB 저장
     *  비밀번호 암호화하여 db 저장
     *  회원가입시 저장
     */
    public MemberUser create (String username , String nickname , String password) {

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
     * joinController 에서 실행
     */
    public void findByNickname (MemberUserCreateForm memberUserCreateForm) {

        Optional<MemberUser> memberUser = memberRepository.findByNickname(memberUserCreateForm.getNickname());

        if (memberUser.isPresent()) {
            throw new IllegalArgumentException("아이디가 존재합니다.");
        }

    }

    public Optional<MemberUser> findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    /**
     *
     * QuestionModifyController 에서 실행
     */
    public MemberUser getUser(String nickname) {

        Optional<MemberUser> findUser = memberRepository.findByNickname(nickname);

        if (findUser.isPresent()) {
            return findUser.get();
        }
        throw new IllegalArgumentException("아이디가 존재합니다.");
    }

    /**
     * 멤버 아이디
     */
    public MemberUser findById(Long id) {
        Optional<MemberUser> memberUser = this.memberRepository.findById(id);
        if(memberUser.isPresent()){
            return memberUser.get();
        }
            throw new IllegalArgumentException("멤버를 찾을 수 없습니다");
    }

    /**
     * 전체 회원 조회
     */
    public Page<MemberUser> getMemberUserList(int page) {

        Pageable pageable = PageRequest.of( page -1, 10); //페이지 당 글 10개씩 표시

        return memberRepository.findAll(pageable);
    }

    /**
     * 유료 회원 여부 저장
     */
    @Transactional
    public void updateMemberIsPaid(MemberUser memberUser, boolean isPaid) {

        memberUser.setPaid(isPaid);
        memberRepository.save(memberUser); //isPaid 수정
    }

    /**
     * 사용자 권한 확인
     */
    public Boolean isPaidMember(Authentication authentication) {
        // 인증 객체에서 사용자명(username)을 가져옴
        String nickname = authentication.getName();

        // 사용자명을 기반으로 멤버 정보를 가져옴
        MemberUser memberUser = getUser(nickname);

        // 멤버 정보가 있고, 유료 멤버십 상태라면 true 반환
        return memberUser != null && memberUser.isPaid();
    }

    @Transactional
    public MemberUser whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImgUrl) {

        Optional<MemberUser> OpMember = findByNickname(nickname);

        if (OpMember.isPresent()) {
           return OpMember.get();
        }
            return create(username , nickname , "");
    }
}



