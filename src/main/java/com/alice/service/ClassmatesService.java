package com.alice.service;

import com.alice.bean.Classmates;
import com.alice.bean.ClassmatesExample;
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
    public void saveClassmates(Classmates classmates){
        classmatesMapper.insertSelective(classmates);//新增数据，不需要带返回数据


    }
    //检验用户名是否可以用，当fanhuitrue说明查询到了该名字的存储过的信息，此时返回false
    public boolean checkname(String classmate_Name){
        ClassmatesExample classmatesExample=new ClassmatesExample();
        ClassmatesExample.Criteria criteria=classmatesExample.createCriteria();
        criteria.andNameEqualTo(classmate_Name);
        long count=classmatesMapper.countByExample(classmatesExample);//查到的记录数
         return count==0;

    }
}
