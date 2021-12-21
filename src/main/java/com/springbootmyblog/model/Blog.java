package com.springbootmyblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//博客
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private Long id;
    private String title;//标题
    private String  content;//内容
    private String description;//博客描述
    private String firstImage;//文章图片
    private String flag;//标记
    private Integer views;//浏览次数
    private boolean appreciation;//赞赏功能
    private boolean reprintStatement;//转载声明
    private boolean commentabled;//评论
    private boolean published;//是否发布
    private boolean recommend;//是否推荐
    private Date createTime;//创建时间
    private Date updateTime;//更新时间

    private Type type;
    private List<Comment> comments=new ArrayList<>();
    private List<Tag> tags=new ArrayList<>();
    private User user;


    //获取多个标签的ID
    private String tagIds;

    //这个属性用来在mybatis中进行连接查询的
    private Long typeId;
    private Long userId;

    private Integer mark;

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }



}
