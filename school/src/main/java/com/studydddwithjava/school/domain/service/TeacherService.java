package com.studydddwithjava.school.domain.service;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    @Qualifier("mysql-teacher")
    private ITeacherRepository teacherRepository;

    public Teacher login(Teacher teacher) {
        return this.teacherRepository.findByUserNameAndPw(teacher);
    }

    public boolean isExistSameName(Teacher teacher) {
        return this.teacherRepository.findByUserName(teacher.getName()).isPresent();
    }
}
