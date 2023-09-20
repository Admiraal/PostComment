package com.hsleiden.api.controller;

import com.hsleiden.api.dao.PostDAO;
import com.hsleiden.api.exception.NotFoundException;
import com.hsleiden.api.model.ApiResponse;
import com.hsleiden.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    @Autowired
    private PostDAO postDao;


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse all(){
        return new ApiResponse(HttpStatus.ACCEPTED, this.postDao.all());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse get(@PathVariable long id){
        Post post;
        try {
            post = this.postDao.getById(id);
        } catch(NotFoundException exception) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "No post with that id");
        }

        return new ApiResponse(HttpStatus.ACCEPTED, post);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse replace(@RequestBody Post post, @PathVariable long id){
        try{
            this.postDao.replace(post, id);
        } catch(NotFoundException exception){
            return new ApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "You replaced post: " + post.getId() + " successfully.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ApiResponse update(@RequestBody Post post, @PathVariable long id){
        try{
            this.postDao.update(post, id);
        } catch(NotFoundException exception){
            return new ApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "You updated post: " + post.getId() + " successfully.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse delete(@PathVariable long id){
        try{
            this.postDao.delete(id);
        } catch(NotFoundException exception){
            return new ApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "You deleted post: " + id + " successfully.");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse save(@RequestBody Post newPost){
        Post post = this.postDao.save(newPost);
        return new ApiResponse(HttpStatus.ACCEPTED, post);
    }

}
