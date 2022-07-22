package cn.kgc.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.TeacherMapper;
import cn.kgc.exam.pojo.TeacherInfo;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService{
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public TeacherInfo findTeacher(String teacherAccount, String teacherPwd) {
		
		return teacherMapper.findTeacher(teacherAccount,teacherPwd);
	}

	@Override
	public int getAllTeacher() {
		
		return teacherMapper.getAllTeacher();
	}
	@Override
	public List<TeacherInfo> getListTeacherInfo() {
		return teacherMapper.getListTeacherInfo();
	}

	@Override
	public TeacherInfo getTeacherById(Integer id) {

		return teacherMapper.getTeacherById(id);
	}

	@Override
	public boolean updateTeacher(TeacherInfo teacherInfo) {
		Integer num=teacherMapper.updateTeacher(teacherInfo);
		return num > 0;
	}

	@Override
	public boolean addTeacher(TeacherInfo teacherInfo) {
		Integer num=teacherMapper.addTeacher(teacherInfo);
		return num>0;
	}

	@Override
	public boolean delTeacher(Integer id) {
		Integer num=teacherMapper.delTeacher(id);
		return num>0;
	}

}
