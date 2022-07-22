package cn.kgc.exam.service;

import cn.kgc.exam.pojo.TeacherInfo;

import java.util.List;

public interface TeacherService {

	TeacherInfo findTeacher(String teacherAccount, String teacherPwd);

	int getAllTeacher();
	List<TeacherInfo> getListTeacherInfo();

	TeacherInfo getTeacherById(Integer id);

	boolean updateTeacher(TeacherInfo teacherInfo);

	boolean addTeacher(TeacherInfo teacherInfo);

	boolean delTeacher(Integer id);

}
