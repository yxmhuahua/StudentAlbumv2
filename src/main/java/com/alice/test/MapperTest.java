package com.alice.test;

import com.alice.bean.Classmates;
import com.alice.bean.Grade;
import com.alice.dao.ClassmatesMapper;

import com.alice.dao.GradeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith指定要用的测试模块，指定了spring所以可以直接autowired要使用的组件
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration指定spring配置文件的位置
@ContextConfiguration(locations = {"classpath:spring_config.xml"})
public class MapperTest {

    @Autowired
    ClassmatesMapper classmatesMapper;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    SqlSession sqlsession;//批量操作的mapper，在spring xml单独配置的bean
    @Test
    public void testCURD() {
        /**
         * Spring的单元测试可以自动住入我们需要的组件
         * 测试mapper
         * 1.创建SpringIOC容器
         * 2.从容器中获取mapper
         * ApplicationContext ioc=new ClassPathXmlApplicationContext("E:\\java project\\IdeaProjects\\StudentAlbum\\src\\main\\resources\\spring_config.xml")
         * ClassmatesMapper bean = ioc.getBean(ClassmatesMapper.class);
         * */
        //System.out.println(classmatesMapper);

      Classmates classmate=classmatesMapper.selectByPrimaryKey(1);
       // classmatesMapper.insertSelective(new Classmates(88,"jury","M","8766656@qq.com",2));
        sqlsession.getMapper(ClassmatesMapper.class);
        System.out.println(classmate);
    }
}
