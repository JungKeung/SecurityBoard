package com.cos.security1.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@ToString
@Entity
@Getter @Setter
@Data
public class User {

    @Id
    private String email;

    private String password;
    private String nickname; //작성자
    private String role; //ROLE_USER,ROLE_ADMIN
    //약관 동의
    private Long consent;
    private Long disagree;


}
