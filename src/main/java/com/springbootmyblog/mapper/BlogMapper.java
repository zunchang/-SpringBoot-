package com.springbootmyblog.mapper;

import com.springbootmyblog.model.Blog;
import com.springbootmyblog.model.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {


    Blog getBlog(Long id);  //后台展示博客

    Blog getDetailedBlog(Long id);  //博客详情

    List<Blog> getAllBlog();  //博客列表展示

    List<Blog> getByTypeId(Long typeId);  //根据类型id获取博客

    List<Blog> getByTagId(Long tagId);  //根据标签id获取博客

    List<Blog> getIndexBlog();  //主页博客展示

    List<Blog> getAllRecommendBlog();  //推荐博客展示

    List<Blog> getSearchBlog(String query);  //全局搜索博客

    List<Blog> searchAllBlog(Blog blog);  //后台根据标题、分类、推荐搜索博客

    List<String> findGroupYear();  //查询所有年份，返回一个集合

    List<Blog> findByYear(String year);  //按年份查询博客

    int saveBlog(Blog blog);

    int saveBlogAndTag(BlogAndTag blogAndTag);

    int updateBlogAndTag(BlogAndTag blogAndTag);

    int updateBlog(Blog blog);

    int viewIncrease(Blog blog);

    int deleteBlog(Long id);



}
