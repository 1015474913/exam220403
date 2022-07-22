package cn.kgc.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;

public interface ExamSubjectMiddleInfoMapper {

	List<ExamSubjectMiddleInfo> viewSubjects(Integer examPaperId);

	int removeSubject(@Param("subjectId") Integer subjectId, @Param("examPaperId") Integer examPaperId);// ɾ���Ծ��е���Ŀ

	ExamSubjectMiddleInfo getChooseSubId(@Param("subjectId") Integer subjectId,
			@Param("examPaperId") Integer examPaperId);// �����Ծ����Ƿ����������

	int addExamSubjectMiddleInfo(@Param("subjectId") Integer subjectId, @Param("examPaperId") Integer examPaperId);

	List<Integer> listExamPaper(Integer examPaperId);// ��ѯ�Ծ�������������Id

}
