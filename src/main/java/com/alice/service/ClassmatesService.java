package com.alice.service;

import com.alice.bean.Classmates;
import com.alice.bean.Msg;
import com.alice.dao.ClassmatesMapper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassmatesService {
    @Autowired
    ClassmatesMapper classmatesMapper;

    public Classmates getId(int id){
        return classmatesMapper.selectByPrimaryKey(id);
    }
    public  List<Classmates> getAll(){
        return classmatesMapper.selectByExampleWithGrade(null);
    }
    public Msg saveClassmates(Classmates classmates){
        classmatesMapper.insertSelective(classmates);//新增数据，不需要带返回数据
        return Msg.sueccess();

    }
}
