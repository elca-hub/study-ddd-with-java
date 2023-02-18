package com.studydddwithjava.school.domain.service;

import com.studydddwithjava.school.domain.model.student.IStudentRepository;
import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.infrastructure.mysql.context.StudentTeamMembershipContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    @Qualifier("mysql-student")
    private IStudentRepository studentRepository;
    @Autowired
    private StudentTeamMembershipContext studentTeamMembershipContext;

    public boolean isExistInTeam(Student student) {
        List<Student> sameTeamStudents = studentRepository.findByTeamId(student.getTeam());

        for (Student target : sameTeamStudents) {
            if (student.getName().getFullName().equals(target.getName().getFullName())) return true;
        }

        return false;
    }
}
