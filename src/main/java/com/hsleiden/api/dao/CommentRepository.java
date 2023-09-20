package com.hsleiden.api.dao;

import com.hsleiden.api.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
