package com.alice.service;

import com.alice.bean.Classmates;
import com.alice.bean.Grade;
import com.alice.dao.ClassmatesMapper;
import com.alice.dao.GradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GradeService {
    @Autowired
    GradeMapper gradeMapper;


    public List<Grade> getAll(){
     List<Grade> gradeList  =gradeMapper.selectByExample(null);
        return gradeList ;
    }
}
