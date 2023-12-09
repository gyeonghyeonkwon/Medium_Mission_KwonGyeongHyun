package com.ll.medium_mission.domain.home.home.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 질문 엔티티
 */
@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String title; //제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    @Column
    private LocalDateTime createDate; //시간

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
