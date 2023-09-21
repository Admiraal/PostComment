package com.hsleiden.api.seeder;

import com.github.javafaker.Faker;
import com.hsleiden.api.dao.CommentDAO;
import com.hsleiden.api.dao.PostDAO;
import com.hsleiden.api.model.Comment;
import com.hsleiden.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CommentSeeder {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private PostDAO postDAO;


    public void seed(Faker faker){
        ArrayList<Post> posts = postDAO.all();

        for (int i = 0; i < posts.size(); i++) {
            Comment comment1 = new Comment();
            comment1.setName(faker.name().firstName()+" "+faker.name().lastName());
            comment1.setBody(faker.lebowski().quote());
            Post post = posts.get(i);
            comment1.setPost(post);
            commentDAO.save(comment1);

            Comment comment2 = new Comment();
            comment2.setName(faker.name().firstName()+" "+faker.name().lastName());
            comment2.setBody(faker.lebowski().quote());
            comment2.setPost(posts.get(i));
            commentDAO.save(comment2);
        }
    }
}
