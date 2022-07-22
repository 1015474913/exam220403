package cn.kgc.exam.mapper;

import java.util.List;

import cn.kgc.exam.pojo.ClassInfo;
import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.GradeInfo;

public interface GradeMapper {

	List<GradeInfo> getListGrade(@Param("gradeId") Integer gradeId);

	GradeInfo showGrade(@Param("gradeId") Integer gradeId);

	List<GradeInfo> getGradeList();

	GradeInfo getGradeById(@Param("gradeId") Integer gradeId);

	List <GradeInfo> getListGradeInfo();

	List<ClassInfo> getClassInfoById(Integer id);

	Integer addGrade(GradeInfo gradeInfo);

	Integer updateGradeInfo(GradeInfo gradeInfo);

	Integer delGrade(Integer id);
}
