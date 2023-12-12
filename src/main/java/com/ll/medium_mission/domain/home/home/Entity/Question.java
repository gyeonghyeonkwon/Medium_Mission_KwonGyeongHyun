package com.ll.medium_mission.domain.home.home.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 질문 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title; //제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    private LocalDateTime createDate; //시간

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)

    private List<Answer> answerList;

    //로그인 유저 엔티티 , 글 목록에 표시 하기 위함
    @ManyToOne
    private MemberUser author;


}
