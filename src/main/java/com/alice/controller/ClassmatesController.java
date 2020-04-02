package com.alice.controller;

import com.alice.bean.Classmates;
import com.alice.bean.Msg;
import com.alice.service.ClassmatesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
//org.springframework.stereotype.Controller注解类型用于指示Spring类的实例是一个控制器
public class ClassmatesController {
    @Autowired
    ClassmatesService classmatesService;//注入
//    @RequestMapping("/classmatesModel")
     @RequestMapping("/classmates")
    public  String getClassmates(@RequestParam(value ="pn",defaultValue = "1")Integer pn, Model model)
    {
        //引入mybatis的分页插件，在查询之前调用PageHelper.startPage并传入页码以及每页的大小
        PageHelper.startPage(pn,5);
        //startPage后面紧跟分页查询

        List<Classmates> classmates=classmatesService.getAll();
        //PageInfo包装查询后的结果，封装了详细信息和查询出来的数据，传入连续显示的页数
        //测试PageInfo全部属性
        //PageInfo包含了非常全面的分页属性
        PageInfo page = new PageInfo(classmates,5);
        //page.setNavigatePages(5);
        model.addAttribute("PageInfo",page);
        //System.out.println(page);
        //page.getNavigatepageNums();连续显示的页数
        return "classmatesList";//此处返回该值之后可以由视图解析器处理来到配置的/WEB-INF/views/路径下，
    }

    @RequestMapping("/classmatesJson")
    @ResponseBody
    public Msg getClassmatesWithJson(@RequestParam(value ="pn",defaultValue = "1")Integer pn){

        PageHelper.startPage(pn,5);
        //startPage后面紧跟分页查询
        List<Classmates> classmates=classmatesService.getAll();
        //PageInfo包装查询后的结果，封装了详细信息和查询出来的数据，传入连续显示的页数
        //测试PageInfo全部属性
        //PageInfo包含了非常全面的分页属性
        PageInfo page = new PageInfo(classmates,5);
        //return page;
        //page.setNavigatePages(5);
        return Msg.sueccess().add("PageInfo",page);
    }

    @RequestMapping(value = "/classmateInfo",method = RequestMethod.POST)
    @ResponseBody
    //REST风格操作数据-员工保存
    public Msg saveClassmates(Classmates classmates)
    {
        System.out.println(classmates);
        classmatesService.saveClassmates(classmates);
         return Msg.sueccess();
    }


    @RequestMapping(value = "/classmateInfo",method = RequestMethod.GET)
    //没有在对应的Controller类中添加对GET请求的处理方法。虽然并没有使用get请求，
    // 但是在进入首页加载表单的时候，默认就是个get请求，而恰好这个请求被笔者的设置拦截了，
    // 所以当请求被拦截后又找不到对应的处理方法，报出了这个错误。
    @ResponseBody
    //REST风格操作数据-员工保存
    public Msg saveClassmates2(Classmates classmates)
    {
        //@RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
        classmatesService.saveClassmates(classmates);
        return Msg.sueccess();
    }








}
