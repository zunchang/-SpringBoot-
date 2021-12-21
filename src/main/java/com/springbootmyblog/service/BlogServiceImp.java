package com.springbootmyblog.service;

import com.springbootmyblog.handler.NotFoundException;
import com.springbootmyblog.mapper.BlogMapper;
import com.springbootmyblog.model.Blog;
import com.springbootmyblog.model.BlogAndTag;
import com.springbootmyblog.model.Tag;
import com.springbootmyblog.util.MarkdownUtils;
import com.sun.xml.internal.bind.v2.model.core.MaybeElement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BlogServiceImp implements BlogService{
    @Resource
    private BlogMapper blogMapper;

    @Override
    public Blog getBlog(Long id) {
        return blogMapper.getBlog(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getByTypeId(Long id) {
        return blogMapper.getByTypeId(id);
    }

    @Override
    public List<Blog> getByTagId(Long id) {
        return blogMapper.getByTagId(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogMapper.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String,List<Blog>> map=new HashMap<>();
        for(String year:set){
            map.put(year,blogMapper.findByYear(year));
        }

        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.getAllBlog().size();
    }


    @Transactional
    @Override
    public Integer saveBlog(Blog blog) {

        if(blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        //保存博客
        blogMapper.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogMapper.saveBlogAndTag(blogAndTag);
        }

        return 1;
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Transactional
    @Override
    public Integer upDateBlog(Blog blog) {
        blog.setUpdateTime(new Date());

        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogMapper.updateBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }
    @Transactional
    @Override
    public Integer deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {

        return blogMapper.searchAllBlog(blog);
    }

    //Markdown->HTML
    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogMapper.getDetailedBlog(id);
        //实现浏览次数增加
        int i=blogMapper.viewIncrease(blog);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

}
