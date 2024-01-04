package com.ll.medium_mission.domain.home.home.Repository;

import com.ll.medium_mission.domain.home.home.Entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question , Long >{
    /**
     * 나의 글 정보 최신날짜 순
     */
    List<Question> findByAuthor_NicknameOrderByCreateDateDesc(String username);

    /**
     *
     * 체크박스 에 체크를 하지 않는 글 들만 불러 오기 위함
     * 최신 작성 날짜 순으로 저장
     */
//    Page<Question> findByIsPublishedFalseOrderByCreateDateDesc(Pageable pageable); //페이징

    @Query("SELECT DISTINCT q FROM Question q " +
            "LEFT JOIN FETCH q.author u1 " +
            "WHERE (q.title LIKE %:kw% OR q.content LIKE %:kw% OR u1.nickname LIKE %:kw%) " +
            "AND q.isPublished = false " +
            "ORDER BY q.createDate DESC")

    Page<Question> findAll(@Param("kw") String kw , Pageable pageable); //페이징

//    Page<Question> findAll(Specification<Question> spec, Pageable pageable); //페이징

    /**
     *
     * @Query("SELECT DISTINCT q FROM Question q " +
     *         "LEFT JOIN FETCH q.author u1 " +
     *         "WHERE (q.title LIKE %:kw% OR q.content LIKE %:kw% OR u1.nickname LIKE %:kw%) " +
     *         "AND q.isPublished = false " +
     *         "ORDER BY q.createDate DESC")
     */

}
