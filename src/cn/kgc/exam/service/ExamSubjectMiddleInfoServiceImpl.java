package cn.kgc.exam.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.kgc.exam.mapper.ExamPaperMapper;
import cn.kgc.exam.mapper.ExamSubjectMiddleInfoMapper;
import cn.kgc.exam.mapper.SubjectMapper;
import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;
import cn.kgc.exam.pojo.SubjectInfo;

@Service
public class ExamSubjectMiddleInfoServiceImpl implements ExamSubjectMiddleInfoService {
	@Autowired
	ExamSubjectMiddleInfoMapper examSubjectMiddleInfoMapper;
	@Autowired
	ExamPaperMapper examPaperMapper;
	@Autowired
	SubjectMapper subjectMapper;

	@Override
	public List<ExamSubjectMiddleInfo> viewSubjects(Integer examPaperId) {
		return examSubjectMiddleInfoMapper.viewSubjects(examPaperId);
	}

	// 移除试题
	@Override
	public boolean removeSubject(Integer subjectId, Integer examPaperId, Integer score) {
		int n = examSubjectMiddleInfoMapper.removeSubject(subjectId, examPaperId);// 删除中间表里的信息
		int m = examPaperMapper.minuScore(score, examPaperId);// 修改试卷表里的数量和分数
		if (n > 0 & m > 0) {
			return true;
		}
		return false;
	}

	@Override
	public ExamSubjectMiddleInfo getChooseSubId(Integer subjectId, Integer examPaperId) {// 判断是否有重复的试题
		return examSubjectMiddleInfoMapper.getChooseSubId(subjectId, examPaperId);
	}

	@Override
	@Transactional
	public int addExamSubjectMiddleInfo(String subjectIds, Integer examPaperId) {
		Integer row = 0;

		String[] subIds = subjectIds.split(",");

		Integer totalScore = 0;
		for (String subId : subIds) {
			int sid = Integer.parseInt(subId);
			row += examSubjectMiddleInfoMapper.addExamSubjectMiddleInfo(sid, examPaperId);
			// 获取试题
			SubjectInfo sub = subjectMapper.getSubject(sid);
			totalScore += sub.getSubjectScore();
		}
		// 修改试卷里面的数量和分数
		examPaperMapper.updateExamPaperSubs(subIds.length, totalScore, examPaperId);
		return row;
	}

	@Override
	public boolean autoAddSubject(Integer examPaperId, Integer subjectSum, Integer courseId, Integer gradeId,
			Integer subjectEasy) {

		List<Integer> list1 = examSubjectMiddleInfoMapper.listExamPaper(examPaperId);// 查询试卷中的id集合
		List<Integer> list2 = subjectMapper.listSubjectsId(courseId, gradeId, subjectEasy);// 查询试题表中符合条件的id集
		for (Integer integer : list2) {
			if (list1.contains(integer)) {
				list2.remove(integer);
			}
		}
		if (list2.size() < subjectSum) {
			return false;
		}
		Integer countScore = 0;// 总分数
		int n = 0;// 添加到中间表的影响行数
		for (int i = 0; i < subjectSum; i++) {
			int index = new Random().nextInt(list2.size());
			int id = list2.get(index);
			// for (Integer arr : list2) {
			SubjectInfo subjectInfo = subjectMapper.getSubject(id);
			n = examSubjectMiddleInfoMapper.addExamSubjectMiddleInfo(id, examPaperId);// 把试题加到中间表
			countScore += subjectInfo.getSubjectScore();// 总分数
			// }
			// break;
			list2.remove(index);
		}
		int m = examPaperMapper.updateExamPaperSubs(subjectSum, countScore, examPaperId);// 修改试卷表里的分数和总题数
		if (m > 0 & n > 0) {
			return true;
		}
		return false;
	}

}
