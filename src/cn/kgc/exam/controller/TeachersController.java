package cn.kgc.exam.controller;

import cn.kgc.exam.pojo.TeacherInfo;
import cn.kgc.exam.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/edu/teachers")
public class TeachersController {
    @Autowired
    TeacherService teacherService;


    @RequestMapping("/teachers")
    public String getTeacherList(Model model,Integer startPage){

        if (startPage==null){
            startPage=1;
        }
        PageHelper.startPage(startPage,10);
        List<TeacherInfo> listTeacherInfo = teacherService.getListTeacherInfo();
        PageInfo<TeacherInfo> pageInfo=new PageInfo<>(listTeacherInfo);
        model.addAttribute("pageNow", startPage);
        model.addAttribute("pageTotal", pageInfo.getPages());
        listTeacherInfo.forEach(System.out::println);
        model.addAttribute("teachers", listTeacherInfo);
        return "admin/teachers";
    }

    @RequestMapping("/teacher/{id}")
    public String getTeacherById(@PathVariable Integer id,Model model){
        TeacherInfo teachers = teacherService.getTeacherById(id);
        model.addAttribute("teacher",teachers);
        return "admin/teacheredit";
    }
    @RequestMapping("/submitteacher")
    @ResponseBody
    public String submitTeacher(TeacherInfo teacherInfo){

        JSONObject jsonObject=new JSONObject();
        boolean flag=teacherService.updateTeacher(teacherInfo);
        if (flag){
            return jsonObject.toJSONString();
        }
        jsonObject.put("error","error");
        return jsonObject.toJSONString();
    }
    @RequestMapping("/addteacher")
    public String addteacher(){
        return "admin/teacheredit";
    }
    @RequestMapping("addsubmitteacher")
    @ResponseBody
    public String addsubmitteacher(TeacherInfo teacherInfo){
        System.out.println(teacherInfo+"=========教师添加=========");
        JSONObject jsonObject=new JSONObject();
        boolean flag=teacherService.addTeacher(teacherInfo);
        if (flag){
            return jsonObject.toJSONString();
        }
        jsonObject.put("error","error");
        return jsonObject.toJSONString();
    }
    @RequestMapping("/del/{id}")
    public String delTeacher(@PathVariable Integer id){
        boolean flag=teacherService.delTeacher(id);
        if (flag){
            return "redirect:http://localhost:8080/exam220402/edu/teachers/teachers";
        }
        return"error";
    }


}
