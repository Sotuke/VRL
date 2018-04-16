package com.blopto.web.repository;


import com.blopto.web.bean.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Long countByUserId(Long userId);
    @Query("select p from Post p inner join p.user a where a.id = :id")
    List<Post> findByUserId(@Param("id") Long id);
}