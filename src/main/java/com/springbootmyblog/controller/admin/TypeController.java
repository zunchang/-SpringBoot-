package com.springbootmyblog.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;
import com.springbootmyblog.service.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                        Model model){
        if(pageNum<=0)
            pageNum=1;
        PageHelper.startPage(pageNum,2);
        List<Type> types=typeService.listType();
        PageInfo<Type> pageInfo=new PageInfo<>(types);
            model.addAttribute("pageInfo", pageInfo);
            model.addAttribute("typeList",types);
        return "admin/types";
    }

    //新增
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //编辑
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id,Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        Integer integer=typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        Type type1=typeService.getNameType(type.getName());
        if(type1!=null){
            attributes.addFlashAttribute("message","分类已存在");
        }else {
            Integer t=typeService.saveType(type);

            if(t==null){
                attributes.addFlashAttribute("message","操作失败");
            }
            else {
                attributes.addFlashAttribute("message","操作成功");
            }
        }

        return "redirect:/admin/types";
    }
    //编辑
    @PostMapping("/types/{id}")
    public String modify(Type type,@PathVariable Integer id,RedirectAttributes attributes){
        Type type2=typeService.getNameType(type.getName());
        if(type2!=null){
            attributes.addFlashAttribute("message","分类已存在");
        }else {
            Integer t=typeService.upDataType(type);

            if(t==null){
                attributes.addFlashAttribute("message","更新失败");
            }
            else {
                attributes.addFlashAttribute("message","更新成功");
            }
        }
        return "redirect:/admin/types";
    }
}
