package com.springbootmyblog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootmyblog.model.Blog;
import com.springbootmyblog.model.Type;
import com.springbootmyblog.model.User;
import com.springbootmyblog.service.BlogService;
import com.springbootmyblog.service.TagService;
import com.springbootmyblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    //开场展示
    @GetMapping("/blogs")
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                            Model model){
        if(pagenum<0)
            pagenum=0;
        PageHelper.startPage(pagenum,5);
        List<Blog>  blogs=blogService.getAllBlog();
        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);
        model.addAttribute("types",typeService.listType());
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogsList",blogs);
        return "admin/blogs";
    }

    //根据条件查询
    @PostMapping("/blogs/search")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                         Model model, Blog blog){
        if(pagenum<0)
            pagenum=0;
        PageHelper.startPage(pagenum,5);
        List<Blog> blogs=blogService.searchAllBlog(blog);
        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("blogsList",blogs);
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }

    //博客新增
    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog",new Blog());
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());

        return "admin/blogs-input";
    }

    //新增博客
    @PostMapping("/blogs")
    public String post(Blog blog, HttpSession session , RedirectAttributes attributes){

        //获取用户信息
        blog.setUser((User) session.getAttribute("user"));
        //获取用户id
        blog.setUserId(blog.getUser().getId());


        Type type=typeService.getNameType(blog.getType().getName());

        //设置blog的type
        blog.setType(typeService.getType(blog.getType().getId()));
        //给blog中的List<Tag>赋值
  /*cuo*/      blog.setTags(tagService.getTagByString(blog.getTagIds()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        Integer integer;
        if(blog.getId()==null){
            integer = blogService.saveBlog(blog);
        }else{
            integer=blogService.upDateBlog(blog);
        }
        if (integer!=null){
            attributes.addFlashAttribute("message", "操作成功");
        }else {
            attributes.addFlashAttribute("message", "操作失败");
        }

        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/input") //去编辑博客页面
    public String editInput(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        blog.init();   //将tags集合转换为tagIds字符串
        model.addAttribute("blog", blog);     //返回一个blog对象给前端th:object
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id,RedirectAttributes attributes){
        Integer integer=blogService.deleteBlog(id);
        if (integer!=null){
            attributes.addFlashAttribute("message","操作成功");
        }else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return "redirect:/admin/blogs";
    }







}



