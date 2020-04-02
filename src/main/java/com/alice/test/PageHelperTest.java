package com.alice.test;

import com.alice.bean.Classmates;
import com.alice.dao.ClassmatesMapper;
import com.alice.dao.GradeMapper;
import com.alice.service.ClassmatesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration指定spring配置文件的位置
@ContextConfiguration(locations = {"classpath:spring_config.xml"})
public class PageHelperTest {
    @Autowired
    ClassmatesMapper classmatesMapper;
    @Autowired
    ClassmatesService classmatesService;//注入
    @Autowired
    SqlSession sqlsession;//批量操作的mapper，在spring xml单独配置的bean
    @Test
    public void test(){

        PageHelper.startPage(1, 10);
        Classmates classmates=classmatesService.getId(1);
        sqlsession.getMapper(ClassmatesMapper.class);
        System.out.println(classmates);
        //用PageInfo对结果进行包装
//        PageInfo page = new PageInfo((List) classmates);
//        System.out.println(page.getPageNum());
    }
    @Test
    public void test2(){

        PageHelper.startPage(1, 5);
        List<Classmates> classmates=classmatesService.getAll();
        sqlsession.getMapper(ClassmatesMapper.class);
        //System.out.println(classmates);
        //用PageInfo对结果进行包装
        PageInfo page = new PageInfo(classmates);
        //System.out.println(page.getSize());
      List<Classmates> list=page.getList();
        //System.out.println(list);
        //System.out.println(page.toString());
        for (Classmates i : list) {
           // System.out.println(i.getName());
        }
        //System.out.println(page.toString());
    }
}
