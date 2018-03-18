package com.blopto.web.blopto.service;

import com.blopto.web.blopto.bean.Post;

import java.util.List;

import com.blopto.web.blopto.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository repository;

    @Override
    public List<Post> findAll() {


        List<Post> posts = (List<Post>) repository.findAll();

        return posts;
    }

    @Override
    public Post save(Post post) {
        repository.save(post);
        return post;
    }
}