package cn.kgc.exam.service;

import java.util.List;

import cn.kgc.exam.pojo.CourseInfo;

public interface CourseService {

	List<CourseInfo> getCourseList();
	List<CourseInfo> getListCourses();

	CourseInfo getCoursesById(Integer id);

	boolean updateCourse(CourseInfo courseInfo);

	boolean addCourse(CourseInfo courseInfo);

	boolean delCourses(Integer id);
	
}
