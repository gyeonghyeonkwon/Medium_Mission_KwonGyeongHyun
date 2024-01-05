package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Entity.MemberUser;
import com.ll.medium_mission.domain.home.home.Entity.Question;
import com.ll.medium_mission.domain.home.home.Repository.QuestionRepository;
import com.ll.medium_mission.global.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * 엔티티 DB에 저장
     */
    public void write(String title, String content, MemberUser author, Boolean isPublished, Boolean isPaid) {
        Question q = new Question();
        q.setTitle(title);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(author);
        q.setIsPublished(isPublished); // 공개 여부
        q.setIsPaid(isPaid); //유료 여부
        this.questionRepository.save(q);
    }

    /**
     * 질문 게시글 리스트
     * <p>
     * 질문 DB 테이블 에 저장되어있는 데이터를 조회
     * 체크박스 에 체크 가 되어 있지 않은 글들만 찾음
     */
    public Page<Question> getList(int page , String kw , String searchType) {

        List<Sort.Order> sorts = new ArrayList<>();

        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of( page -1, 10 , Sort.by(sorts)); //페이지 당 글 10개씩 표시 ,최신 날짜순

        switch (searchType) {
            case "title":
                return questionRepository.findByTitleContainingAndIsPublishedFalse(kw, pageable); //셀렉트 박스 에서 제목 이 선택 되고 검색이 되면 실행
            case "content":
                return questionRepository.findByContentContainingAndIsPublishedFalse(kw, pageable); //셀렉트 박스 에서 내용 이 선택 되고 검색이 되면 실행
            case "author":
                return questionRepository.findByAuthorNicknameContainingAndIsPublishedFalse(kw, pageable); //셀렉트 박스 에서 작성자가 선택 되고 검색이 되면 실행
            case "titleAndContent":
                return questionRepository.findByTitleContainingOrContentContainingAndIsPublishedFalse(kw, kw, pageable); //셀렉트 박스 에서 제목과 내용이 선택 되고 검색이 되면 실행
            default:
                throw new IllegalArgumentException("Invalid searchType: " + searchType);
        }

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
    public void modifySave(Question question ,String title, String content ,Boolean isPublished ,  Boolean isPaid) {
        question.setTitle(title);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now()); //수정일시 저장
        question.setIsPublished(isPublished); // 공개 여부 수정
        question.setIsPaid(isPaid); // 유료 여부 수정
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