package com.ll.medium_mission.domain.home.home.Repository;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question , Long> {

    List<Question> findByAuthor_NicknameOrderByCreateDateDesc(String username);

}
