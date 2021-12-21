package com.springbootmyblog.mapper;

import com.springbootmyblog.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User examination(String username,String password);
}
