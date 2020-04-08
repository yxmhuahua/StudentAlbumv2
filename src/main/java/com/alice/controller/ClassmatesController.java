package com.alice.controller;

import com.alice.bean.Classmates;
import com.alice.bean.Msg;
import com.alice.service.ClassmatesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.beans.factory.annotation.Value;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
/*import org.springframework.validation.annotation.Validated;*/
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//org.springframework.stereotype.Controller注解类型用于指示Spring类的实例是一个控制器
public class ClassmatesController {
    @Autowired
    ClassmatesService classmatesService;//注入
    //rest风格请求


    //删除由单个删除改成多个，所以两种情况都可以

    @RequestMapping(value = "/Classmate/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delectClassmate(@PathVariable("ids") String ids)
    {
        if(ids.contains("-"))
        {
            String[] str_ids = ids.split("-");
            //组装集合
            List<Integer> del_ids = new ArrayList<Integer>();
            for (String string : str_ids)
            {
                del_ids.add(Integer.parseInt(string));//类型转换

            }
            classmatesService.delectBath( del_ids );

        }
        else {
            Integer id=Integer.parseInt(ids);

            classmatesService.delectClassmate(id);
        }
        return Msg.sueccess();
    }

    //更新保存编辑后的信息
    @RequestMapping(value = "/Classmate/{id}",method = RequestMethod.PUT)
    @ResponseBody//HttpServletRequest解决封装不上的问题
    public Msg updateClassmateInfo(Classmates classmates, HttpServletRequest request) {

        classmatesService.updateClassmate(classmates);
        return Msg.sueccess();
    }



    @RequestMapping(value = "/Classmate/{id}" ,method = RequestMethod.GET)
    @ResponseBody
    //@PathVariable指定是路径中的id的值
    public Msg findClassmateInfo(@PathVariable("id") Integer id)
    {

        Classmates classmates=classmatesService.getId(id);



        return Msg.sueccess().add("classmate",classmates);

    }





    //    @RequestMapping("/classmatesModel")
    @RequestMapping(value = "/classmates")
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

    /*    要想支持JSR303校验需要如下：
         若是tomcat7以上版本服务器直接可导包用，tomcat7以下服务器由于el表达式功能不够，
         需要额外给服务器的lib包替换芯的标准的el
        导入Hibernate-Validator
    */
    @RequestMapping(value = "/classmateInfo",method = RequestMethod.POST)
    @ResponseBody
    //REST风格操作数据-员工保存@Valid代表开启校验
    public Msg saveClassmates(@Valid Classmates classmates, BindingResult result)
    {
        // System.out.println(classmates);
        if(result.hasErrors())
        {
            //校验失败返回失败在模态框中提示错误信息
            Map<String,Object> map = new HashMap<String, Object>();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError fieldError:errors)
            {

                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("JSRerrorFields",map);

        }
        else {
            classmatesService.saveClassmates(classmates);
            return Msg.sueccess();
        }

    }




    //没有在对应的Controller类中添加对GET请求的处理方法。虽然并没有使用get请求，
    // 但是在进入首页加载表单的时候，默认就是个get请求，而恰好这个请求被笔者的设置拦截了，
    // 所以当请求被拦截后又找不到对应的处理方法，报出了这个错误。
   /* @ResponseBody//return值对象变成json
    //REST风格操作数据-员工保存
    public Msg saveClassmates2(Classmates classmates)
    {
        //@RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
        classmatesService.saveClassmates(classmates);
        return Msg.sueccess();
    }*/


    @RequestMapping(value = "/classmateInfo",method = RequestMethod.GET)
    @ResponseBody
    //REST风格操作数据-员工保存@Valid代表开启校验
    public Msg saveClassmatesPut(@Valid Classmates classmates, BindingResult result)
    {
        // System.out.println(classmates);
        if(result.hasErrors())
        {
            //校验失败返回失败在模态框中提示错误信息
            Map<String,Object> map = new HashMap<String, Object>();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError fieldError:errors)
            {

                System.out.println("错误的字段名：" + fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return Msg.fail().add("JSRerrorFields",map);

        }
        else {
            classmatesService.saveClassmates(classmates);
            return Msg.sueccess();
        }

    }





    @RequestMapping(value = "/checkname",method = RequestMethod.GET)
    @ResponseBody
    //REST风格操作数据-员工保存
    //@RequestParam("name")明确了要取出的数值是哪个，对应实体类的字段(错),是请求和响应的对照？！
    public Msg checkname(@RequestParam("name")String name)
    {
        //@RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解）
        //增加后端校验功能
        String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        boolean tf=name.matches(regx);
        if(!tf)
        {

            return Msg.fail().add("va_msg","用户名必须是2-5位中文名或者6-16位英文名");
        }
        //（优先级），只有用户名合法的时候才会进行重复性校验，也就是说用户名不合法的时候直接报错
        else{
            boolean b_name=classmatesService.checkname(name);
            if(b_name)
            {
                return Msg.sueccess();
            }
            else {
                return  Msg.fail().add("va_msg","用户名重复不可用");//Msg判断不同状态
            }
        }



    }


}
