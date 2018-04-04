package com.blopto.web.service;

import com.blopto.web.bean.Post;
import com.blopto.web.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Long getCount(Long userId) {
        return postRepository.countByUserId(userId);
    }
    public List<Post> findByUserId(Long userId){
        return postRepository.findByUserId(userId);
    }

}