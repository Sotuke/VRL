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
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    private String post;
    private Timestamp date;

    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }
}