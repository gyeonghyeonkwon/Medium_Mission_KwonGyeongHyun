package com.ll.medium_mission.domain.home.home.Repository;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question , Long> {
    /**
     * 나의 글 정보 최신날짜 순
     */
    List<Question> findByAuthor_NicknameOrderByCreateDateDesc(String username);

    /**
     *
     * 체크박스 에 체크를 하지 않는 글 들만 불러 오기 위함
     */
    List<Question>findByIsPublishedFalse();
}
