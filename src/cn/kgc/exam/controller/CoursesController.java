package cn.kgc.exam.controller;

import cn.kgc.exam.pojo.CourseInfo;
import cn.kgc.exam.pojo.GradeInfo;
import cn.kgc.exam.pojo.TeacherInfo;
import cn.kgc.exam.service.CourseService;

import cn.kgc.exam.service.GradeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/edu/courses")
public class CoursesController {

    @Autowired
    CourseService courseService;
    @Autowired
    GradeService gradeService;

    @RequestMapping("/courses")
    public String listCourses(Model model){
       List<CourseInfo> courses = courseService.getListCourses();
       model.addAttribute("courses",courses);
       return "admin/courses";
    }
    @RequestMapping("/courseedit/{id}")
    public String courseedit(@PathVariable Integer id,Model model){

     CourseInfo courseInfo=courseService.getCoursesById(id);
      List<GradeInfo> list=gradeService.getListGradeInfo();
        model.addAttribute("course",courseInfo);
        model.addAttribute("grades",list);
     return "admin/courseedit";
    }
    @RequestMapping("/update")
    @ResponseBody
    public String updateCourse(CourseInfo courseInfo,GradeInfo gradeInfo){        courseInfo.setGrade(gradeInfo);
       boolean flag= courseService.updateCourse(courseInfo);
       JSONObject jsonObject=new JSONObject();

       if (flag){
           return jsonObject.toJSONString();
       }
        return "error";
    }
    @RequestMapping("/preAddCourse")
    public String preAddCourses(Model model){
        List<GradeInfo> list=gradeService.getListGradeInfo();
        model.addAttribute("grades",list);
        return "admin/courseedit";
    }
    @RequestMapping("/addCourseSub")
    @ResponseBody
    public String addCourseSub(CourseInfo courseInfo,GradeInfo gradeInfo){
            courseInfo.setGrade(gradeInfo);
        boolean flag=courseService.addCourse(courseInfo);
        JSONObject jsonObject=new JSONObject();
        if (flag){
            return jsonObject.toJSONString();
        }
        return "error";
    }
    @RequestMapping("/del/{id}")
    public String delCourses(@PathVariable Integer id){
        boolean flag=courseService.delCourses(id);
        if (flag){
            return "redirect:http://localhost:8080/exam220402/edu/courses/courses";
        }
        return "error";
    }


}
