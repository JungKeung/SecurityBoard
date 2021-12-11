package com.cos.security1.controller;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter @Setter
public class BoardForm {

    @NotEmpty(message = "제목은 필수 입니다")
    private String title;

    private String email;
    private String context;

    @CreationTimestamp
    private Date CreatedDate;

}

