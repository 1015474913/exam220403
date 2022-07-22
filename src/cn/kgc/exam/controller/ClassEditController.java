package cn.kgc.exam.controller;


import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.GradeInfo;
import cn.kgc.exam.pojo.TeacherInfo;
import cn.kgc.exam.service.ClassService;

import cn.kgc.exam.service.GradeService;

import cn.kgc.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/edu/classes")
public class ClassEditController {

    @Autowired
    ClassService classService;

    @Autowired
    GradeService gradeService;

    @Autowired
    TeacherService teacherService;

        @RequestMapping("/classes")
    public String classInfo(Model model){
            List<ClassInfo> classes= classService.getListClassEdit();

            model.addAttribute("classes",classes);
        return "admin/classes";
    }
    @RequestMapping(value = "/update/{id}")
    public String classInfo1(@PathVariable Integer id, Model model){
       ClassInfo editClass= classService.getClassEditById(id);
        model.addAttribute("editClass",editClass);
        List<GradeInfo> gradeInfoList= gradeService.getListGradeInfo();
        model.addAttribute("grades",gradeInfoList);
        List<TeacherInfo> teacherInfoList =teacherService.getListTeacherInfo();
        model.addAttribute("teachers",teacherInfoList);
        return "admin/classedit";
    }

    @RequestMapping(value = "classSubmit",method = RequestMethod.PUT)
    public String classSubmitI(ClassInfo classInfo){
       boolean flag=classService.updateClassInfo(classInfo);
        if(flag){
            return "redirect:http://localhost:8080/exam220402/edu/classes/classes";
        }
        return "error";
    }
    @RequestMapping("/preAddClass")
    public String preAddClass(Model model){
        List<GradeInfo> gradeInfoList= gradeService.getListGradeInfo();
        model.addAttribute("grades",gradeInfoList);
        List<TeacherInfo> teacherInfoList =teacherService.getListTeacherInfo();
        model.addAttribute("teachers",teacherInfoList);

        return "admin/preAddClass";
    }
    @RequestMapping(value = "/addClass",method = RequestMethod.POST)
    public String addClass(ClassInfo classInfo){

        boolean flag= classService.addClass(classInfo);
        if (flag){
            return "redirect:http://localhost:8080/exam220402/edu/classes/classes";
        }
        return "error";
    }
    @RequestMapping("del/{id}")
    public String delClass(@PathVariable Integer id){
        boolean flag = classService.delClassById(id);
        if (flag){
            return "redirect:http://localhost:8080/exam220402/edu/classes/classes";
        }
        return "error";
    }
    @RequestMapping("/preStudentCount")
    public String studentCount(Model model){
        List<GradeInfo> gradeInfoList= gradeService.getListGradeInfo();
        model.addAttribute("grades",gradeInfoList);

            return "admin/charts/studentCount";
    }

}
