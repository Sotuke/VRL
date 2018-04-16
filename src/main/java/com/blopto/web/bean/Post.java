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

    //@ManyToOne
    //@JoinColumn(name = "user_id", nullable = false)
    //private Long userId;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String post;
    private Timestamp date;

    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public User getUser(){
        return user;
    }
}