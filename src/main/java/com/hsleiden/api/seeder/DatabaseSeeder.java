package com.hsleiden.api.seeder;

import com.github.javafaker.Faker;
import com.hsleiden.api.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class DatabaseSeeder {

    @Autowired
    private PostSeeder postSeeder;
    @Autowired
    private CommentSeeder commentSeeder;
    private boolean alreadySeeded = false;


    @EventListener
    public void seed(ContextRefreshedEvent event){
        if(alreadySeeded){
            return;
        }

        Faker faker = new Faker(new Locale("nl"));
        postSeeder.seed(faker);
        commentSeeder.seed(faker);


        this.alreadySeeded = true;
    }
}
