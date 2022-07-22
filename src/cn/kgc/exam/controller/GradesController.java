package cn.kgc.exam.controller;


import cn.kgc.exam.pojo.ClassInfo;
import cn.kgc.exam.pojo.GradeInfo;
import cn.kgc.exam.service.GradeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/edu/grades")
public class GradesController {

    @Autowired
    GradeService gradeService;



    @RequestMapping("/grades")
    public String course(Model model){
        List<GradeInfo> list =gradeService.getListGradeInfo();
        model.addAttribute("grades",list);
        return "admin/grades";
    }
    @RequestMapping("/gradecl")
    @ResponseBody
    public String gradeclass( Integer gradeId ){
        List<ClassInfo> gradeInfo= gradeService.getClassInfoById(gradeId);
        /*Map map = new HashMap();
        map.put("gradeInfo",gradeInfo);

        System.out.println(JSONObject.toJSONString(map));*/

        return JSONObject.toJSONString(gradeInfo);
    }
    @RequestMapping("/preAddGrade")
    public String preAddGrade(){
        return "admin/addgrade";
    }
    @RequestMapping(value = "addGradeSubmit",method = RequestMethod.POST)
    public String addGradeSubmit(GradeInfo gradeInfo){
        System.out.println(gradeInfo+"===================");
       boolean flag= gradeService.addGrade(gradeInfo);
       if (flag){
           return "redirect:http://localhost:8080/exam220402/edu/grades/grades";
       }
        return "error";
    }
    @RequestMapping("/updateGrade/{id}")
    public String updateGrade(@PathVariable Integer id,Model model){
        GradeInfo gradeInfo=gradeService.getGradeById(id);
        System.out.println(gradeInfo);
        model.addAttribute(gradeInfo);
        return "admin/gradeedit";
    }
    @RequestMapping("/updateSubmit")
    public String updateSubmit(GradeInfo gradeInfo){
        System.out.println(gradeInfo+"=======================");
        boolean flag=gradeService.updateGradeInfo(gradeInfo);
        if (flag){
            return "redirect:http://localhost:8080/exam220402/edu/grades/grades";
        }
        return "error";
    }
    @RequestMapping("/delGrade/{id}")
    public String delGrade(@PathVariable Integer id){

        System.out.println(id+"====================");
        boolean flag=gradeService.delGrade(id);
        if (flag){
            return "redirect:http://localhost:8080/exam220402/edu/grades/grades";
        }
        return "error";
    }
}
