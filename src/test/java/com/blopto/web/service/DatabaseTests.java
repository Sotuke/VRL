package com.blopto.web.service;

import com.blopto.web.bean.Post;
import com.blopto.web.bean.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTests {
    @Autowired
    protected PostService postService; //posts
    @Autowired
    protected UserService userService; //users
    private User testUser;
    private List<Post> posts;
    @Before
    public void beforeTest(){
        User user = new User();
        user.setEmail("test@mail.com");
        user.setFirstName("testE");
        user.setLastName("testP");
        user.setPassword("testPassword");
        user.setIdentityNumber("394512245");
        user.setUsername("testUser");
        user.setProvider("testProvider");
        userService.registerUser(user);
        testUser = userService.findByEmail("test@mail.com");
        addPosts(testUser);
        posts = postService.findByUserId(testUser.getId());
    }
    @After
    public void afterTest(){
        userService.removeUser(testUser);
    }
    private List<User> addUsers(){
        List<User> users = new ArrayList<>();
        for (int a = 0; a < 100; a++){
            User user = new User();
            user.setEmail("test@mail.com" + a);
            user.setFirstName("testE" + a);
            user.setLastName("testP" + a);
            user.setPassword("testPassword" + a);
            user.setIdentityNumber("394512245" +a);
            user.setUsername("testUser" + a);
            user.setProvider("testProvider");
            userService.registerUser(user);
            user = userService.findByEmail(user.getEmail());
            users.add(user);
        }
        return users;
    }

    private void removeUsers(List<User> users){
        for (User user: users){
            userService.removeUser(user);
        }
    }

    public void addPosts(User user){
        for (int a = 0; a < 100; a++){
            Post post = new Post();
            post.setPost("test" + a);
            post.setDate();
            post.setUser(user);
            postService.addPost(post);
        }

    }
    @Test
    public void RegisterAndRemoveUserTest(){
        assertSame(userService.countById(),(long)1);
        List<User> users = addUsers();
        assertSame(userService.countById(),(long) 101);
        for (int a = 1; a < 100; a++){
            assertEquals("test@mail.com" + a,users.get(a).getEmail());
        }
        removeUsers(users);
        assertSame(userService.countById(), (long) 1);
        long a = userService.countById();
        assertEquals(a,1);
    }
    @Test
    public void addPostsTest(){
        List<Post> posts1 = postService.getAllPost();
        assertEquals(posts1.size(),100);
        assertEquals(posts1.size(),posts.size());
        List<User> users = addUsers();
        for (User user: users){
            addPosts(user);
        }
        assertEquals(postService.getAllPost().size(),10100);
        removeUsers(users);
        assertEquals(postService.getAllPost().size(),100);
        Post post = new Post();
        post.setUser(testUser);
        post.setDate();
        post.setPost("Töötab?");
        postService.addPost(post);
        List<Post> posts = postService.findByUserId(testUser.getId());
        for (int a = 0; a < 100;a++){
            assertEquals("test" + a,posts.get(a).getPost());
        }
    }
    @Test
    public void postData(){
        Post post = new Post();
        post.setUser(testUser);
        post.setDate();
        Date date = post.getDate();
        post.setPost("Töötab?");
        postService.addPost(post);
        post = postService.findByUserId(testUser.getId()).get(100);
        assertEquals(date,post.getDate());
        assertEquals("Töötab?",post.getPost());
        assertEquals(testUser.getId(),post.getUser().getId());
    }
    @Test
    public void userData(){
        User user = new User();
        user.setEmail("kas@mail.com");
        user.setProvider("testing");
        user.setUsername("kas");
        user.setIdentityNumber("3941");
        user.setPassword("1234");
        user.setLastName("miks");
        user.setFirstName("paul");
        userService.registerUser(user);
        user = userService.findByEmail("kas@mail.com");
        assertEquals("kas@mail.com",user.getEmail());
        assertEquals("kas",user.getUsername());
        assertEquals("3941",user.getIdentityNumber());
        assertEquals("1234",user.getPassword());
        assertEquals("miks",user.getLastName());
        assertEquals("paul",user.getFirstName());
        userService.removeUser(user);
    }
    @Test(expected = Exception.class)
    public void sameEmailRegisterTest() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setFirstName("testE1");
        user.setLastName("testP1");
        user.setPassword("testPa1ssword");
        user.setIdentityNumber("3194512245");
        user.setUsername("testUser1");
        user.setProvider("testProvider");
        userService.registerUser(user);
    }
    @Test(expected = Exception.class)
    public void sameUsernameRegisterTest() {
        User user = new User();
        user.setEmail("test@mail.com1");
        user.setFirstName("testE1");
        user.setLastName("testP1");
        user.setPassword("testPa1ssword");
        user.setIdentityNumber("3194512245");
        user.setUsername("testUser");
        user.setProvider("testProvider");
        userService.registerUser(user);
    }

}
