package cn.kgc.exam.mapper;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.TeacherInfo;

import java.util.List;

public interface TeacherMapper {

	TeacherInfo findTeacher(@Param("teacherAccount")String teacherAccount, @Param("teacherPwd")String teacherPwd);

	int getAllTeacher();
	List<TeacherInfo> getListTeacherInfo();

	TeacherInfo getTeacherById(Integer id);

	Integer updateTeacher(TeacherInfo teacherInfo);

	Integer addTeacher(TeacherInfo teacherInfo);

	Integer delTeacher(Integer id);

}
