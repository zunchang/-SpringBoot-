package com.springbootmyblog.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootmyblog.model.Blog;
import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;
import com.springbootmyblog.service.BlogService;
import com.springbootmyblog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TagShowController {

    @Resource
    private TagService tagService;
    @Resource
    private BlogService blogService;


    @GetMapping("/tags/{id}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        @PathVariable Long id, Model model) {

        PageHelper.startPage(pagenum,20);
        List<Tag> tags=tagService.getBlogTag();
        if(id==-1){
            id=tags.get(0).getId();
        }

        List<Blog> blogs = blogService.getByTagId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);

        return "tags";

    }


}
