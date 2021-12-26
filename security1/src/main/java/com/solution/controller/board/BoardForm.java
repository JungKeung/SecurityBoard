package com.solution.controller.board;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BoardForm {

    @NotEmpty(message = "게시글 제목을 입력해주세요.")
    private String title;

    @NotEmpty(message = "게시글 내용을 입력해주세요.")
    private String content;

}

