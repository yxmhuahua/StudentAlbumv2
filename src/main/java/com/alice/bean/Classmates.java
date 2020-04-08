package com.alice.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Classmates {
    public Integer id;
    //利用springmvc对重要数据进行JSR303后端校验
    @Pattern(regexp = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})",message = "姓名格式有误，必须是2-5位中文名或者6-16位英文名！")//自定义校验规则
    public String name;
    @NotEmpty(message="性别为必选项！")
    public String gender;

    @Email(message = "邮箱格式不正确")
    public  String email;

    public  Integer gradeId;
    //希望查询员工的同时也能查询部门
    public   Grade grade;

    public Grade getGrade() {
        return grade;
    }



    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Classmates()
    {

    }

    public Classmates(Integer id, String name, String gender, String email, Integer gradeId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.gradeId = gradeId;

    }

    @Override
    public String toString() {
        return "Classmates{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", gradeId=" + gradeId +
                ", grade=" + grade +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }
}