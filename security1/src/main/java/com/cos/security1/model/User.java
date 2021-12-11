package com.cos.security1.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_Id")
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String RePassword;
    private String email;
    private String role; //ROLE_USER,ROLE_ADMIN
    @CreationTimestamp
    private Timestamp createDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Board> boardList = new ArrayList<> ();
}
