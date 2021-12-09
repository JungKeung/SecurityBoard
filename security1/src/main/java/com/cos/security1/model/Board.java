package com.cos.security1.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Getter @Setter
@Entity
public class Board {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "board_id")
    private Integer id;


    private String email;
    private String title;
    private String context;

    @CreationTimestamp
    private Date CreatedDate;

    @UpdateTimestamp
    private Date UpdateDate;

    @Builder
    public Board() {
        this.id = id;
        this.email = email;
        this.title = title;
        this.context = context;

    }
}