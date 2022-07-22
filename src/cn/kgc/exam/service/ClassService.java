package cn.kgc.exam.service;

import cn.kgc.exam.pojo.ClassInfo;

import java.util.List;

public interface ClassService {
    List<ClassInfo> getClassList();
    List<ClassInfo> getListClassEdit();

    ClassInfo getClassEditById(Integer id);

    boolean updateClassInfo(ClassInfo classInfo);

    boolean addClass(ClassInfo classInfo);

    boolean delClassById(Integer id);
}
