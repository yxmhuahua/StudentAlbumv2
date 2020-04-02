package com.alice.controller;

import com.alice.bean.Classmates;
import com.alice.bean.Grade;
import com.alice.bean.Msg;
import com.alice.service.GradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GradeController {
    @Autowired
    GradeService gradeService;
    @RequestMapping("/getGrade")
    @ResponseBody
    public Msg getGradeInfo()
    {
        List<Grade> gradesList=gradeService.getAll();
        return Msg.sueccess().add("gradeInfo",gradesList);

    }



}
