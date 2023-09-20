package com.hsleiden.api.seeder;

import com.github.javafaker.Faker;
import com.hsleiden.api.dao.PostDAO;
import com.hsleiden.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostSeeder {

    @Autowired
    private PostDAO postDAO;

    public void seed(Faker faker){
        for (int i = 0; i < 200; i++) {
            Post post1 = new Post();
            post1.setTitle(faker.book().title());
            post1.setBody(String.join(" ", faker.lorem().paragraphs(5)));
            this.postDAO.save(post1);

            Post post2 = new Post();
            post2.setTitle(faker.book().title());
            post2.setBody(String.join(" ", faker.lorem().paragraphs(5)));
            this.postDAO.save(post2);
        }
    }

}
