package com.alice.bean;

public class Classmates {
    public Integer id;

    public String name;

    public String gender;

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