package com.springbootmyblog.service;

import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;

import java.util.List;

public interface TagService {


    int saveTag(Tag tag);

    Tag getNameTag(String name);

    Tag getTag(Integer id);

    List<Tag> listTag();

    List<Tag> getBlogTag();

    List<Tag> getTagByString(String text);   //从字符串中获取tag集合

    Integer upDataTag(Tag tag);

    int deleteTag(Long ID);

}

