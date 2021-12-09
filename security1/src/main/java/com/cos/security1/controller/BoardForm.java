package com.cos.security1.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BoardForm {

    @NotEmpty(message = "제목은 필수 입니다")
    private String title;

    private String email;
    private String context;
}

