package cn.kgc.exam.service;

import java.util.List;

import cn.kgc.exam.pojo.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kgc.exam.mapper.GradeMapper;
import cn.kgc.exam.pojo.GradeInfo;

@Service
public class GradeServiceImpl implements GradeService{
	
	@Autowired
	private GradeMapper gradeMapper;
	
	@Override
	public List<GradeInfo> getGradeList() {
		
		return gradeMapper.getGradeList();
	}
	@Override
	public List<GradeInfo> getListGradeInfo() {

		return gradeMapper.getListGradeInfo();
	}

	@Override
	public List<ClassInfo> getClassInfoById(Integer id) {
		return gradeMapper.getClassInfoById(id);
	}

	@Override
	public boolean addGrade(GradeInfo gradeInfo) {
		Integer num = gradeMapper.addGrade(gradeInfo);

		return num>0;
	}

	@Override
	public GradeInfo getGradeById(Integer id) {
		return gradeMapper.getGradeById(id);
	}

	@Override
	public boolean updateGradeInfo(GradeInfo gradeInfo) {
		Integer num=gradeMapper.updateGradeInfo(gradeInfo);

		return num>0;
	}

	@Override
	public boolean delGrade(Integer id) {
		Integer num=gradeMapper.delGrade(id);
		return num>0;
	}


}
