package com.hsleiden.api.dao;

import com.hsleiden.api.exception.NotFoundException;
import com.hsleiden.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class PostDAO {

    @Autowired
    private PostRepository postRepository;


    public ArrayList<Post> all(){
        ArrayList<Post> allPosts = (ArrayList<Post>) this.postRepository.findAll();
        return allPosts;
    }

    public Post getById(long id){
        Optional<Post> optionalPost = this.postRepository.findById(id);
        if(optionalPost.isEmpty()){
            throw new NotFoundException("Post with id: " + id + "not found");
        }

        return optionalPost.get();
    }

    public Post save(Post newPost){
        Post post = this.postRepository.save(newPost);
        return post;
    }

    public Post replace(Post newPost, long id) throws NotFoundException{
        Optional<Post> optionalPost = this.postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new NotFoundException("Post with id: " + id + " not found");
        }

        Post currentPost = optionalPost.get();
        currentPost.setTitle(newPost.getTitle());
        currentPost.setBody(newPost.getBody());

        this.postRepository.save(currentPost);
        return currentPost;
    }


    public Post update(Post updatedPost, long id) throws NotFoundException{
        Optional<Post> optionalPost = this.postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new NotFoundException("Post with id: " + id + " not found");
        }

        Post currentPost = optionalPost.get();
        currentPost.setTitle(updatedPost.getTitle());
        currentPost.setBody(updatedPost.getBody());

        this.postRepository.save(currentPost);
        return currentPost;
    }

    public void delete(long id) throws NotFoundException{
        Optional<Post> optionalPost = this.postRepository.findById(id);

        if(optionalPost.isEmpty()){
            throw new NotFoundException("Post with id: " + id + " not found");
        }

        Post post = optionalPost.get();
        this.postRepository.delete(post);
    }


}
