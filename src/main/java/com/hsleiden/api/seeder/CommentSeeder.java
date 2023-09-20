package com.hsleiden.api.seeder;

import com.github.javafaker.Faker;
import com.hsleiden.api.dao.CommentDAO;
import com.hsleiden.api.dao.PostDAO;
import com.hsleiden.api.model.Comment;
import com.hsleiden.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Locale;

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
            comment1.setTitle(faker.beer().style());
            comment1.setBody(faker.lebowski().quote());
            Post post = posts.get(i);
            comment1.setPost(post);
            commentDAO.save(comment1);

            Comment comment2 = new Comment();
            comment2.setTitle(faker.beer().style());
            comment2.setBody(faker.lebowski().quote());
            comment2.setPost(posts.get(i));
            commentDAO.save(comment2);
        }
    }
}
