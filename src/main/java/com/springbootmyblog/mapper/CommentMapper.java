package com.springbootmyblog.mapper;

import com.springbootmyblog.model.Comment;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface CommentMapper {

    //根据创建时间倒序来排，根据id查询评论
    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Long blogParentId);

    //查询父级对象
    Comment findByParentCommentId(Long parentcommentid);

    //添加一个评论
    int saveComment(Comment comment);

}
