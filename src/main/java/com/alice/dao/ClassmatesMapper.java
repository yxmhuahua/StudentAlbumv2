package com.alice.dao;

import com.alice.bean.Classmates;
import com.alice.bean.ClassmatesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassmatesMapper {
    long countByExample(ClassmatesExample example);

    int deleteByExample(ClassmatesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Classmates record);

    int insertSelective(Classmates record);

    List<Classmates> selectByExample(ClassmatesExample example);

    Classmates selectByPrimaryKey(Integer id);

    List<Classmates> selectByExampleWithGrade(ClassmatesExample example);

    Classmates selectByPrimaryKeyWithGrade(Integer id);

    int updateByExampleSelective(@Param("record") Classmates record, @Param("example") ClassmatesExample example);

    int updateByExample(@Param("record") Classmates record, @Param("example") ClassmatesExample example);

    int updateByPrimaryKeySelective(Classmates record);

    int updateByPrimaryKey(Classmates record);
}