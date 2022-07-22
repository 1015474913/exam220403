package cn.kgc.exam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.kgc.exam.pojo.ExamPaperInfo;
import cn.kgc.exam.pojo.ExamSubjectMiddleInfo;
import cn.kgc.exam.pojo.GradeInfo;
import cn.kgc.exam.pojo.SubjectInfo;
import cn.kgc.exam.service.ExamPaperService;
import cn.kgc.exam.service.ExamSubjectMiddleInfoService;
import cn.kgc.exam.service.GradeService;
import cn.kgc.exam.service.SubjectService;

@Controller
@RequestMapping("/edu/admin")
public class ExamPaperController {
	@Autowired
	ExamPaperService examPaperService;
	@Autowired
	GradeService gradeService;
	@Autowired
	ExamSubjectMiddleInfoService examSubjectMiddleInfoService;
	@Autowired
	SubjectService subjectService;

	// 锟介看锟斤拷锟斤拷锟皆撅拷
	@RequestMapping("/listExamPaper")
	public String listExamPaper(Model model, Integer startPage) {
		if (startPage == null) {
			startPage = 1;
		}
		PageHelper.startPage(startPage, 8);
		List<ExamPaperInfo> list = examPaperService.getExamPapersList();
		model.addAttribute("examPapers", list);
		PageInfo<ExamPaperInfo> pageInfo = new PageInfo<ExamPaperInfo>(list);
		model.addAttribute("pageInfo", pageInfo);

		model.addAttribute("pageNow", startPage);// 锟斤拷前页
		model.addAttribute("pageTotal", pageInfo.getPages());// 锟斤拷页锟斤拷
		return "admin/examPapers";

	}

	// 删锟斤拷锟皆撅拷
	@RequestMapping("/deleteExamPaper")
	public String deleteExamPaper(Integer examPaperId) {
		boolean flag = examPaperService.deleteExamPaper(examPaperId);
		if (flag) {
			return "redirect:/edu/admin/listExamPaper";
		}
		return "error";
	}

	// 锟睫革拷锟皆撅拷
	@RequestMapping("/getExamPaperById")
	public String getExamPaperById(Integer examPaperId, Model model) {
		ExamPaperInfo exam = examPaperService.getExamPaperById(examPaperId);
		model.addAttribute("examPaper", exam);

		List<GradeInfo> list = gradeService.getGradeList();
		model.addAttribute("grades", list);
		return "admin/examPaperedit";
	}

	// 修改试卷
	@RequestMapping("/updateExamPaper")
	public String updateExamPaper(ExamPaperInfo examPaperInfo) {
		boolean flag = examPaperService.updateExamPaper(examPaperInfo);
		if (flag) {
			return "redirect:/edu/admin/listExamPaper";
		}
		return "error";

	}

	// 去锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	@RequestMapping("/toAddExamPaper")
	public String toAddExamPaper(Model model) {
		List<GradeInfo> list = gradeService.getGradeList();
		model.addAttribute("grades", list);
		return "admin/examPapereAdd";
	}

	// 锟斤拷锟斤拷跃锟�
	@RequestMapping("/addExamPaper")
	public String addExamPaper(ExamPaperInfo examPaperInfo) {
		boolean flag = examPaperService.addExamPaper(examPaperInfo);
		if (flag) {
			return "redirect:/edu/admin/listExamPaper";
		}
		return "error";
	}

	// 查看所有试题
	@RequestMapping(value = "/toViewSubjects", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String toViewSubjects(Integer examPaperId, Model model) {
		List<ExamSubjectMiddleInfo> list = examSubjectMiddleInfoService.viewSubjects(examPaperId);
		String json = JSONObject.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
		return json;
	}

	// 锟狡筹拷锟斤拷锟斤拷
	@RequestMapping(value = "/removeSubjectFromPaper", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String removeSubjectFromPaper(Integer subjectId, Integer examPaperId, Integer score) {
		System.out.println(examPaperId + "," + subjectId);
		boolean flag = examSubjectMiddleInfoService.removeSubject(subjectId, examPaperId, score);
		JSONObject json = new JSONObject();
		if (flag) {
			json.put("result", "true");
		} else {
			json.put("result", "flase");
		}
		return json.toJSONString();

	}

	// 锟介看锟斤拷锟斤拷锟斤拷锟斤拷
	@RequestMapping("/listSubject")
	public String listSubject(Model model, Integer startPage, Integer handAdd, Integer examPaperId) {
		if (startPage == null) {
			startPage = 1;
		}
		PageHelper.startPage(startPage, 6);
		List<SubjectInfo> list = subjectService.getListSubject();
		PageInfo<SubjectInfo> pageInfo = new PageInfo<SubjectInfo>(list);
		// model.addAttribute("subjects", list);
		model.addAttribute("subjects", pageInfo);
		model.addAttribute("pageTotal", pageInfo.getPages());// 锟斤拷页锟斤拷
		model.addAttribute("pageNow", startPage);// 锟斤拷前页
		model.addAttribute("handAdd", handAdd);

		// 锟斤拷锟斤拷锟斤拷侄锟斤拷锟斤拷锟斤拷锟解到锟皆撅拷锟斤拷锟斤拷要锟斤拷锟斤拷锟皆撅拷锟斤拷, 锟揭凤拷锟截碉拷前锟窖撅拷选锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
//		if (examPaperId != null) {
		model.addAttribute("examPaperId", examPaperId);
//			List<String> ids = (List<String>) model.addAttribute("ids");
//			if (ids == null) {
//				model.addAttribute("choosed", 0);
//			} else {
//				model.addAttribute("choosed", ids.size());
//			}
//		}
		return "admin/subjects";
	}

	// 锟斤拷询锟斤拷锟斤拷锟角凤拷锟截革拷
	@RequestMapping("/getChooseSubId")
	@ResponseBody
	public String getChooseSubId(Integer subjectId, Integer examPaperId) {
		ExamSubjectMiddleInfo examSubjectMiddleInfo = examSubjectMiddleInfoService.getChooseSubId(subjectId,
				examPaperId);
		if (examSubjectMiddleInfo != null) {
			return "f-exists-";
		} else {
			return "";
		}
	}

	// 锟街讹拷锟斤拷锟斤拷锟斤拷锟�
	@RequestMapping("/handAdd")
	@ResponseBody
	public String handAdd(Integer examPaperId, String subIds) {
		int n = examSubjectMiddleInfoService.addExamSubjectMiddleInfo(subIds, examPaperId);
		if (n > 0) {
			return "success";
		} else {
			return "error";
		}
	}

	// 去锟皆讹拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	@RequestMapping("/toAutoSubjectSelect")
	public String toAutoSubjectSelect(Integer examPaperId, Model model) {
		model.addAttribute("examPaperId", examPaperId);
		return "admin/autoSubjectSelect";
	}

	// 锟皆讹拷锟斤拷锟斤拷锟斤拷锟�
	@RequestMapping(value = "/autoAddSubject", method = RequestMethod.POST)
	@ResponseBody
	public String autoAddSubject(Integer examPaperId, Integer subjectSum, Integer courseId, Integer gradeId,
			Integer subjectEasy) {
		boolean flag = examSubjectMiddleInfoService.autoAddSubject(examPaperId, subjectSum, courseId, gradeId,
				subjectEasy);
		if (flag) {
			return "true";
		}
		return "false";
	}
}
