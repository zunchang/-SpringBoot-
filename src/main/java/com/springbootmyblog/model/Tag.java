package com.springbootmyblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//标签
public class Tag {
    private Long id;
    private String name;
    private List<Blog> blogs=new ArrayList<>();

}
