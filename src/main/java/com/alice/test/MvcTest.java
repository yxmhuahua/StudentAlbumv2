package com.alice.test;

import com.alice.bean.Classmates;
import com.alice.dao.ClassmatesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
//spring4需要servlet3版本

//使用spring测试模块提供的测试请求功能，测试crud请求准确性
//@RunWith指定要用的测试模块，指定了spring所以可以直接autowired要使用的组件

//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
//WebAppConfiguration用来拿到web容器  可以@Autowired ioc容器自己WebApplicationContext
@WebAppConfiguration
//@ContextConfiguration指定spring配置文件的位置
@ContextConfiguration(locations = {"classpath:spring_config.xml"})
public class MvcTest {
    //传入SpringMvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取到处理结果。
    MockMvc mockMvc;
    @Before
    public void initMokcMvc()
    {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }

//    @Test
//    public void contextTest()
//    {
//        //读取applicationContext配置文件
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:springmvc_config.xml","classpath:spring_config.xml");
//         //获取mapper代理对象
//        ClassmatesMapper mapper = context.getBean(ClassmatesMapper.class);
//
//      //执行查询语句
//        ClassmatesMapper example = new ClassmatesMapper();
//        List<ClassmatesMapper> list = mapper.selectByExample(example);
//
//      //设置PageHelper分页信息，1表示当前第1页，10表示当前页的条数为10
//      PageHelper.startPage(1, 10);
//
//        //获取分页信息
//      PageInfo<Classmates> pageInfo=new PageInfo<>(list);
//        System.out.println(pageInfo);
//
//
//    }
//


    @Test
    public void testPage() throws Exception {
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
        //模拟请求拿到返回值
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.get("/classmates")
                .param("pn","5")).andReturn();
        //请求成功后，请求域中会有pageInfo，我们可以取出pageInfo进行验证；
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
        System.out.println("当前页码"+pi.getPageNum());
        System.out.println("总页码"+pi.getPages());
    }




 }
