package com.blopto.web.blopto.service;

import com.blopto.web.blopto.bean.Tweet;
import java.util.List;

import com.blopto.web.blopto.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService implements ITweetService {

    @Autowired
    private TweetRepository repository;

    @Override
    public List<Tweet> findAll() {


        List<Tweet> tweets = (List<Tweet>) repository.findAll();

        return tweets;
    }

    @Override
    public Tweet save(Tweet tweet) {
        repository.save(tweet);
        return tweet;
    }
}