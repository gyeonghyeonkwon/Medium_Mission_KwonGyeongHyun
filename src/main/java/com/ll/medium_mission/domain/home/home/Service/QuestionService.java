package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Repository.QuestionRepository;
import com.ll.medium_mission.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 엔티티 DB에 저장
     */
    public void write(String title, String content, MemberUser author , Boolean isPublished) {
       Question q = new Question();
       q.setTitle(title);
       q.setContent(content);
       q.setCreateDate(LocalDateTime.now());
       q.setAuthor(author);
       q.setIsPublished(isPublished); //체크 박스 true / false 를 받기 위함
        this.questionRepository.save(q);
    }

    /**
     * 질문 게시글 리스트
     * <p>
     * 질문 DB 테이블 에 저장되어있는 데이터를 조회
     * 체크박스 에 체크 가 되어 있지 않은 글들만 찾음
     */
    public List<Question> getList() {

        return this.questionRepository.findByIsPublishedFalse();
    }

    /**
     * db에서 아이디값을 찾을 수 없는 경우 404 예외 발생
     *
     * 저장 되어 있는 게시글 클릭 시 조회수 1 씩 증가 (수정 예정)
     */
    public Question getQuestion(Long id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            Question questionView = question.get();
            questionView.setView(questionView.getView()+1);
            this.questionRepository.save(questionView);
            return questionView;
        }
        else {
            throw new NotFoundException("해당하는 게시판을 찾을수없습니다 ");
        }
    }

    /**
     *
     * 수정 내용 저장
     */
    public void modifySave(Question question ,String title, String content ) {
        question.setTitle(title);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);

    }

    /**
     *
     * 글 삭제
     */
    public void delete(Long id) {

        questionRepository.deleteById(id);

    }

    /**
     *  최근 작성 순으로 나의 글 불러 오기
     */
    public List<Question>getUserMyList(String username) {

        List<Question> userQuestions = questionRepository.findByAuthor_NicknameOrderByCreateDateDesc(username);

        if (userQuestions.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자의 게시글이 없습니다.");
        }

        return userQuestions;
    }


}