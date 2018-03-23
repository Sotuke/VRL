package com.blopto.web.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String post;
    private Timestamp date;
    public Post(){
    }

    public Long getId(){
        return id;
    }
    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    public Timestamp getDate(){
        return date;
    }

    public void setDate() {
        this.date = new Timestamp(System.currentTimeMillis());
    }
}