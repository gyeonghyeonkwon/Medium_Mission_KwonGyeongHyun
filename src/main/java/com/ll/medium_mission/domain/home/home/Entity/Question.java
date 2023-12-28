package com.ll.medium_mission.domain.home.home.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 질문 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title; //제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @Column(columnDefinition = "integer default 0", nullable = false) //조회수는 0부터 시작
    private int view; //조회수

    @CreatedDate
    private LocalDateTime createDate; //생성 시간

    @CreatedDate
    private  LocalDateTime modifyDate; // 수정 시간

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)

    private List<Answer> answerList;

    //로그인 유저 엔티티 , 글 목록에 표시 하기 위함
    @ManyToOne
    private MemberUser author;

    private Boolean isPublished;  //boolean 은 맞으나 null 값을 허용 하기 위해 Boolean 으로 작성함

    private Boolean isPaid; //유료 글 여부
}
