package com.springbootmyblog.service;

import com.springbootmyblog.mapper.TagMapper;
import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImp implements TagService{

    @Resource
    private TagMapper tagMapper;

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getNameTag(String name) {
        return tagMapper.getNameTag(name);
    }

    @Override
    public Tag getTag(Integer id) {
        return tagMapper.getTag(id);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }

    @Override
    public List<Tag> getBlogTag() {
        return tagMapper.getBlogTag();
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Integer> longs = convertToList(text);
        for (Integer long1 : longs) {
            tags.add(tagMapper.getTag(long1));
        }
        return tags;
    }
    private List<Integer> convertToList(String ids) {  //把前端的tagIds字符串转换为list集合
        List<Integer> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Integer(idarray[i]));
            }
        }
        return list;
    }

    @Transactional
    @Override
    public Integer upDataTag(Tag tag) {
        return tagMapper.upDataTag(tag);
    }

    @Override
    public int deleteTag(Long ID) {
        return tagMapper.deleteTag(ID);
    }
}
