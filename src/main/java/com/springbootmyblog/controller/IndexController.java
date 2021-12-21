package com.springbootmyblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootmyblog.model.Blog;
import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;
import com.springbootmyblog.service.BlogService;
import com.springbootmyblog.service.CommentService;
import com.springbootmyblog.service.TagService;
import com.springbootmyblog.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private BlogService blogService;


    @Resource
    private TypeService typeService;

    @Resource
    private TagService tagService;


    @RequestMapping("/")
    public String index(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum, Model model){

        List<Blog> allBlog=blogService.getIndexBlog();
        List<Type> allType=typeService.getBlogType();
        List<Tag> allTag=tagService.getBlogTag();
        List<Blog> recommendBlog=blogService.getAllRecommendBlog();
        PageHelper.startPage(pagenum,8);
        PageInfo pageInfo=new PageInfo(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tags",allTag);
        model.addAttribute("types",allType);
        model.addAttribute("recommendBlogs",recommendBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         @RequestParam String query,Model model){
        PageHelper.startPage(pagenum, 5);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo pageInfo = new PageInfo(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);

        return "search";

    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        Blog blog = blogService.getDetailedBlog(id);
        model.addAttribute("blog", blog);
        return "blog";
    }
}
