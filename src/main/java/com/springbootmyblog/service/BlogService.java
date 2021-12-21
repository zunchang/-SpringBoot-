package com.springbootmyblog.service;

import com.springbootmyblog.model.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {

    Blog getBlog(Long id);

    List<Blog> getAllBlog();

    List<Blog> getAllRecommendBlog();  //推荐博客展示

    List<Blog> getByTypeId(Long id) ; //根据类型id获取博客

    List<Blog> getByTagId(Long id);  //根据标签id获取博客

    Map<String,List<Blog>> archiveBlog();  //归档博客

    int countBlog();

    Integer saveBlog(Blog blog);

    List<Blog> getIndexBlog(); //首页博客显示

    List<Blog> getSearchBlog(String query);  //全局搜索博客

    Integer upDateBlog(Blog blog);

    Integer deleteBlog(Long id);

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    Blog getDetailedBlog(Long id);  //前端展示博客



}
