package com.blopto.web.blopto.service;

import com.blopto.web.blopto.bean.Tweet;

import java.util.List;

public interface ITweetService {

    public List<Tweet> findAll();
}