package com.springbootmyblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
//评论
public class Comment {
    private Long id;
    private String avatar;//头像
    private String email;
    private String nickname;//昵称
    private String content;//内容
    private Date createTime;//创建时间
    private List<Comment> replyComments=new ArrayList<>();
    private Comment parentComment;
    private Long parentCommentId;//父评论Id
    private Long blogId;
    private Blog blog;
}
