package com.ll.medium_mission.domain.home.home.Service;

import com.ll.medium_mission.domain.home.home.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    
}
