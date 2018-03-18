package com.blopto.web.blopto.service;

import com.blopto.web.blopto.bean.Post;

import java.util.List;

public interface IPostService {

    public List<Post> findAll();
    public Post save(Post post);
}