package com.blopto.web.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idpost")
    private Long idPost;
    private String post;
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name="iduser")
    private User user;

    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }
}