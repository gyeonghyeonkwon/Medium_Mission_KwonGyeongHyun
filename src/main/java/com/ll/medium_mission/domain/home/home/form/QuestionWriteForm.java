package com.ll.medium_mission.domain.home.home.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class QuestionWriteForm {

        private Long id;

        @NotBlank(message = "제목을 입력해주세요")
        @Size
        private String title;

        @NotBlank(message = "내용을 입력 해주세요")
        @Size
        private String content;

        private Boolean  isPublished; //유료 회원

        private Boolean isPaid; //유료 글 여부

}
