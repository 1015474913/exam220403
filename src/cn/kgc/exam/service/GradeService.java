package cn.kgc.exam.service;

import java.util.List;

import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.GradeInfo;

public interface GradeService {

	List<GradeInfo> getGradeList();
	List<GradeInfo> getListGradeInfo();

	List<ClassInfo> getClassInfoById(Integer id);

	boolean addGrade(GradeInfo gradeInfo);

	GradeInfo getGradeById(Integer id);

	boolean updateGradeInfo(GradeInfo gradeInfo);

	boolean delGrade(Integer id);

}
