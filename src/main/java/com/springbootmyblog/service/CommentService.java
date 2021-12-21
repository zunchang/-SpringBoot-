package com.springbootmyblog.service;

import com.springbootmyblog.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CommentService {
    List<Comment> listComentByBlogId(Long blogId);

    int saveComment(Comment comment);
}
