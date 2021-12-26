package com.solution.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@ToString
@Entity
@Getter @Setter
@Data
public class User {

    @Id
    /**
     * 유저 이메일
     */
    private String email;

    /**
     * 유저 패스워드
     */
    private String password;

    /**
     * 유저 닉네임
     */
    private String nickname; //작성자

    /**
     * 유저 권한 (sequrity)
     * ROLE_USER,ROLE_ADMIN
     */
    private String role;

    /**
     * 개인정보 이용약관 (필수)
     */
    private Boolean isAgreePrivacyTerms;

    /**
     * 마케팅 이용약관 (선택)
     */
    private Boolean isAgreeMarketingTerms;
}