package com.springbootmyblog.mapper;

import com.springbootmyblog.model.Tag;
import com.springbootmyblog.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface TagMapper {

    Integer saveTag(Tag tag);//添加

    Tag getTag(Integer id);

    Tag getNameTag(String name);//查询

    List<Tag> listTag();//查询全部

    List<Tag> getBlogTag();  //首页展示博客标签

    Tag upDataTag(Long id,Tag tag);//根据id修改数据

    Integer deleteTag(Long ID);

    Integer upDataTag(Tag tag);
}
