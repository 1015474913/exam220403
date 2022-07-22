package cn.kgc.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.CourseMapper;
import cn.kgc.exam.pojo.CourseInfo;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public List<CourseInfo> getCourseList() {
		
		return courseMapper.getCourseList();
	}
	@Override
	public List<CourseInfo> getListCourses() {
		return courseMapper.getListCourses();
	}

	@Override
	public CourseInfo getCoursesById(Integer id) {
		return courseMapper.getCoursesById(id);
	}

	@Override
	public boolean updateCourse(CourseInfo courseInfo) {
		Integer num = courseMapper.updateCourse(courseInfo);
		return num>0;
	}

	@Override
	public boolean addCourse(CourseInfo courseInfo) {
		Integer num = courseMapper.addCourse(courseInfo);
		return num>0;
	}

	@Override
	public boolean delCourses(Integer id) {
		Integer num = courseMapper.delCourses(id);
		return num>0 ;
	}
	
	
}
