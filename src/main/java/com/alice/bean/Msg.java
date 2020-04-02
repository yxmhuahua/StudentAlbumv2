package com.alice.bean;

import java.util.HashMap;
import java.util.Map;

//通用的带返回状态的类-JSON
public class Msg {
    private int code;//状态码 100-成功 200-失败

    private  String msg;//返回的提示信息

    private Map<String,Object> extend=new HashMap<String, Object>();//用户要返回给浏览器的数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

//静态方法来直接调用类的方法即可
    public static Msg sueccess(){
        Msg result=new Msg();
        result.setCode(100);
        result.setMsg("处理成功!");
        return result;
    }
    public static Msg fail(){
        Msg result=new Msg();
        result.setCode(200);
        result.setMsg("处理失败!");
        return result;
    }
    //定义add方法 使得结果可以链式操作携带数据
    public Msg add(String key,Object value){
        this.getExtend().put(key, value);
        return this;

    }
}
