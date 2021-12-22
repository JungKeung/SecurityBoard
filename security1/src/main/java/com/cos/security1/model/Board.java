package com.cos.security1.model;


import lombok.Data;
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
@Data
public class Board {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private String context;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "email")
    private User user;
}