package com.hsleiden.api.controller;


import com.hsleiden.api.dao.CommentDAO;
import com.hsleiden.api.exception.NotFoundException;
import com.hsleiden.api.model.ApiResponse;
import com.hsleiden.api.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse all(){
        return new ApiResponse(HttpStatus.ACCEPTED, this.commentDAO.all());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse get(@PathVariable long id){
        Comment comment;
        try {
            comment = this.commentDAO.getById(id);
        } catch(NotFoundException exception) {
            return new ApiResponse(HttpStatus.NOT_FOUND, "No post with that id");
        }

        return new ApiResponse(HttpStatus.ACCEPTED, comment);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse replace(@RequestBody Comment comment, @PathVariable long id){
        try{
            this.commentDAO.replace(comment, id);
        } catch(NotFoundException exception){
            return new ApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "You replaced comment: " + comment.getId() + " successfully.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public ApiResponse update(@RequestBody Comment comment, @PathVariable long id){
        try{
            this.commentDAO.update(comment, id);
        } catch(NotFoundException exception){
            return new ApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "You updated comment: " + comment.getId() + " successfully.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse delete(@PathVariable long id){
        try{
            this.commentDAO.delete(id);
        } catch(NotFoundException exception){
            return new ApiResponse(HttpStatus.NOT_FOUND, exception.getMessage());
        }

        return new ApiResponse(HttpStatus.ACCEPTED, "You deleted comment: " + id + " successfully.");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse save(@RequestBody Comment newComment){
        Comment comment = this.commentDAO.save(newComment);
        return new ApiResponse(HttpStatus.ACCEPTED, comment);
    }


}
