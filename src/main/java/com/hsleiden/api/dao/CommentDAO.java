package com.hsleiden.api.dao;

import com.hsleiden.api.exception.NotFoundException;
import com.hsleiden.api.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class CommentDAO {


    @Autowired
    private CommentRepository commentRepository;

    public Comment save(Comment newComment){
        Comment comment = this.commentRepository.save(newComment);
        return comment;
    }

    public ArrayList<Comment> all(){
        ArrayList<Comment> allComments = (ArrayList<Comment>) this.commentRepository.findAll();
        return allComments;
    }

    public Comment getById(long id){
        Optional<Comment> optionalComment = this.commentRepository.findById(id);
        if(optionalComment.isEmpty()){
            throw new NotFoundException("Comment with id: " + id + "not found");
        }

        return optionalComment.get();
    }


    public Comment replace(Comment newComment, long id) throws NotFoundException{
        Optional<Comment> optionalComment = this.commentRepository.findById(id);

        if(optionalComment.isEmpty()){
            throw new NotFoundException("Comment with id: " + id + " not found");
        }

        Comment currentComment = optionalComment.get();
        currentComment.setName(newComment.getName());
        currentComment.setBody(newComment.getBody());

        this.commentRepository.save(currentComment);
        return currentComment;
    }


    public Comment update(Comment updatedComment, long id) throws NotFoundException{
        Optional<Comment> optionalComment = this.commentRepository.findById(id);

        if(optionalComment.isEmpty()){
            throw new NotFoundException("Comment with id: " + id + " not found");
        }

        Comment currentComment = optionalComment.get();
        currentComment.setName(updatedComment.getName());
        currentComment.setBody(updatedComment.getBody());

        this.commentRepository.save(currentComment);
        return currentComment;
    }

    public void delete(long id) throws NotFoundException{
        Optional<Comment> optionalComment = this.commentRepository.findById(id);

        if(optionalComment.isEmpty()){
            throw new NotFoundException("Comment with id: " + id + " not found");
        }

        Comment comment = optionalComment.get();
        this.commentRepository.delete(comment);
    }

}
