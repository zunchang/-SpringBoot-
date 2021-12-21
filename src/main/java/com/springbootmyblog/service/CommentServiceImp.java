package com.springbootmyblog.service;

import com.springbootmyblog.mapper.CommentMapper;
import com.springbootmyblog.model.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService{

    @Resource
    private CommentMapper commentMapper;

    private List<Comment> tempReplys=new ArrayList<>();

    @Override
    public List<Comment> listComentByBlogId(Long blogId) {

        List<Comment> listComment=commentMapper.findByBlogIdAndParentCommentNull(blogId, Long.parseLong("-1"));

        //子评论赋值
        setChildren(listComment,listComment);

        //获取顶点评论
        List<Comment> ParentCommentNull=listParentCommentNull(listComment);

        combineChildren(ParentCommentNull);


        return ParentCommentNull;
    }

    private void combineChildren(List<Comment> comments){

        for(Comment comment : comments){
            List<Comment> replys=comment.getReplyComments();
            for(Comment reply : replys){
                recursively(reply);
            }
            comment.setReplyComments(tempReplys);
            tempReplys=new ArrayList<>();
        }
    }

    //递归迭代取出子评论
    private void recursively(Comment comment){
        tempReplys.add(comment);
        if(comment.getReplyComments().size()>0){
            List<Comment> replys=comment.getReplyComments();
            for(Comment reply:replys){
                tempReplys.add(reply);
                if(reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }

    //遍历设置子评论
    private void setChildren(List<Comment> ParentComments,List<Comment> listComments){
        List<Comment> reply=new ArrayList<>();
        for(Comment ParentComment:ParentComments){
            for(Comment listComment:listComments){
                if(listComment.getParentCommentId()==-1){
                    continue;
                }
                if(ParentComment.getId()==listComment.getParentCommentId()){
                    reply.add(listComment);
                    listComment.setParentComment(ParentComment);
                }
            }
            ParentComment.setReplyComments(reply);
            reply=new ArrayList<>();
        }
    }
    //得到无父评论的评论
    private List<Comment> listParentCommentNull(List<Comment> comments){

        List<Comment> ParentCommentNull=new ArrayList<>();
        for(Comment comment:comments){
            Comment c=new Comment();
            BeanUtils.copyProperties(comment,c);
            if(c.getParentCommentId()==-1){
                ParentCommentNull.add(c);
            }
        }
        return ParentCommentNull;
    }


    @Transactional
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId=comment.getParentComment().getId();
        if(parentCommentId!=-1){
            //有父级评论
            comment.setParentComment(commentMapper.findByParentCommentId(comment.getParentCommentId()));
        }else {
            //没有父级评论
            comment.setParentCommentId((long) -1);
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentMapper.saveComment(comment);
    }
}
