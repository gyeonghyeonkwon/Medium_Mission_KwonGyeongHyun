package com.ll.medium_mission.domain.home.home.Repository;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberUser , Long> {

    Optional<MemberUser> findByUsername(String username);
    Optional<MemberUser> findByNickname(String nickname);



}
