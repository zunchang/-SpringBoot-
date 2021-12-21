package com.springbootmyblog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootmyblog.model.Blog;
import com.springbootmyblog.model.Type;
import com.springbootmyblog.service.BlogService;
import com.springbootmyblog.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TypeShowController {


    @Resource
    private TypeService typeService;
    @Resource
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        @PathVariable Long id, Model model) {

        PageHelper.startPage(pagenum,5);
        List<Type> types=typeService.getBlogType();

        if(id==-1){
            id=types.get(0).getId();
        }

        List<Blog> blogs = blogService.getByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);

        return "types";

    }


}
