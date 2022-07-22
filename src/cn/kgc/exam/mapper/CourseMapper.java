package cn.kgc.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.CourseInfo;

public interface CourseMapper {
	List<CourseInfo>  getListCourse(@Param("courseId")Integer courseId);

	List<CourseInfo> getCourseList();

	CourseInfo getCourse(@Param("courseId")Integer courseId);
	//liuFang
	List<CourseInfo> getListCourses();

	CourseInfo getCoursesById(Integer id);

	Integer updateCourse(CourseInfo courseInfo);

	Integer addCourse(CourseInfo courseInfo);

	Integer delCourses(Integer id);

}
