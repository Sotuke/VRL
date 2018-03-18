package com.blopto.web.blopto.repository;


import com.blopto.web.blopto.bean.Tweet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {

}
