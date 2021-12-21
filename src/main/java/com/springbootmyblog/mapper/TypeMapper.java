package com.springbootmyblog.mapper;

import com.springbootmyblog.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Mapper
@Repository
public interface TypeMapper {

    Integer saveType(Type type);//添加

    Type getType(Long id);

    Type getNameType(String name);//查询

    List<Type> listType();//查询全部

    List<Type> getBlogType();//查询对应的博客数量

    Type upDataType(Long id,Type type);//根据id修改数据

    Integer deleteType(Long ID);

    Integer upDataType(Type type);
}
