package com.cos.security1.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@ToString
@Getter @Setter
@Entity
public class Board {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "board_id")
    private Integer id;
    private String username; // 회원아이디 = 게시글 작성자

    private String email;
    private String title;
    private String context;

    @CreationTimestamp
    private Date CreatedDate;

    @UpdateTimestamp
    private Date UpdateDate;

    @ManyToOne
    @JoinColumn(name="User_Id")
    private User user;
}