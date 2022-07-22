package cn.kgc.exam.service;

import cn.kgc.exam.mapper.ClassMapper;
import cn.kgc.exam.pojo.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassInfo> getClassList() {

        return classMapper.showListClass();
    }
    @Override
    public List<ClassInfo> getListClassEdit() {

        return classMapper.getListClassEdit();
    }

    @Override
    public ClassInfo getClassEditById(Integer id) {
        return classMapper.getClassEditById(id);
    }

    @Override
    public boolean updateClassInfo(ClassInfo classInfo) {
        Integer num = classMapper.updateClassInfo(classInfo);
        return num > 0;
    }

    @Override
    public boolean addClass(ClassInfo classInfo) {
        classInfo.setGradeId(classInfo.getGrade().getGradeId());
        classInfo.setTeacherId(classInfo.getTeacher().getTeacherId());
        Integer num = classMapper.addClass(classInfo);
        return num > 0;
    }

    @Override
    public boolean delClassById(Integer id) {
        Integer num = classMapper.delClassById(id);
        return num > 0;
    }

}
