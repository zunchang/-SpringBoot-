package com.springbootmyblog.service;

import com.springbootmyblog.model.Type;

import java.util.List;

public interface TypeService {



    int saveType(Type type);

    Type getNameType(String name);

    Type getType(Long id);

    List<Type> listType();

    List<Type> getBlogType();

    Integer upDataType(Type type);

    int deleteType(Long ID);
}
