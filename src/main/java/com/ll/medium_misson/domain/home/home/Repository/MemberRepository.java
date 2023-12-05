package com.ll.medium_misson.domain.home.home.Repository;

import com.ll.medium_misson.domain.home.home.MemberUser.MemberUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberUser , Long> {
}
