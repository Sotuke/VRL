package com.blopto.web.repository;


import com.blopto.web.bean.Post;
import com.blopto.web.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public final static String FIND_BY_ADDRESS_QUERY = "SELECT p " +
            "FROM Person p LEFT JOIN p.addresses a " +
            "WHERE a.address = :address";

    Long countByUserId(Long userId);

    @Query("select p from post p left join p.user a where a.idUser = :idUser")
    List<Post> findByUserId(@Param("idUser") Long idUser);
}