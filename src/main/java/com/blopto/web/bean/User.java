package com.blopto.web.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")

public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @NotNull
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Post> post;
    //@Column(unique=true)
    //@NotNull
    private String username;
    private String identityNumber;
    private String firstName;
    private String lastName;
    private String password;

}