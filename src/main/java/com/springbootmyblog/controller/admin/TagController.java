package com.springbootmyblog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;
import com.springbootmyblog.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){
        if(pageNum<=0)
            pageNum=1;
        PageHelper.startPage(pageNum,4);
        List<Tag> tags=tagService.listTag();
        PageInfo<Tag> pageInfo=new PageInfo<>(tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tagList",tags);
        return "admin/tags";
    }

    //新增
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }


    //编辑
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Integer id,Model model){
        model.addAttribute("tags",tagService.getTag(id));
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        Integer integer=tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

    @PostMapping("/tags")
    public String post(Tag tag, RedirectAttributes attributes){
        Tag tag1=tagService.getNameTag(tag.getName());
        if(tag1!=null){
            attributes.addFlashAttribute("message","分类已存在");
        }else {
            Integer t=tagService.saveTag(tag);

            if(t==null){
                attributes.addFlashAttribute("message","操作失败");
            }
            else {
                attributes.addFlashAttribute("message","操作成功");
            }
        }

        return "redirect:/admin/tags";
    }
    //编辑
    @PostMapping("/tags/{id}")
    public String modify(Tag tag,@PathVariable Integer id,RedirectAttributes attributes){
        Tag tag2=tagService.getNameTag(tag.getName());
        if(tag2!=null){
            attributes.addFlashAttribute("message","分类已存在");
        }else {
            Integer t=tagService.upDataTag(tag);

            if(t==null){
                attributes.addFlashAttribute("message","更新失败");
            }
            else {
                attributes.addFlashAttribute("message","更新成功");
            }
        }
        return "redirect:/admin/tags";
    }

}
