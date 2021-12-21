package com.springbootmyblog.service;

import com.springbootmyblog.mapper.TypeMapper;
import com.springbootmyblog.model.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImp implements TypeService{

    @Resource
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }


    @Override
    public Type getNameType(String name) {
        return typeMapper.getNameType(name);
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public List<Type> listType() {
        List<Type> types=typeMapper.listType();
        return types;
    }

    @Override
    public List<Type> getBlogType(){
        return typeMapper.getBlogType();
    }

    @Transactional
    @Override
    public Integer upDataType(Type type) {
        return typeMapper.upDataType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }
}
