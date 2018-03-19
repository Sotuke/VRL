package com.blopto.web.blopto.bean.PostDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private String post;

    public PostDTO(String post){
        this.post = post;
    }
    public String getPost(){
        return post;
    }


}
