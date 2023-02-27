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

    /**
     * studentと等しい名前が同じチーム内にいるかをチェック
     * @param student 検査する対象
     * @return studentと同じチームに入っていて名前が等しいstudentがいるか
     */
    public boolean isExistInTeam(Student student) {
        List<Student> sameTeamStudents = studentRepository.findByTeamId(student.getTeam());

        for (Student target : sameTeamStudents) {
            if (student.getName().equals(target.getName())) return true;
        }

        return false;
    }

    public boolean isExistStudentNumberInTeam(Student student) {
        List<Student> sameStudentNumberMembers = studentRepository.findByStudentNumber(student.getStudentNumber());

        return sameStudentNumberMembers.size() != 0;
    }

    public int makeStudentNumber() {
        int maxStudentNumber = studentRepository.fetchMaxStudentNumber();

        return maxStudentNumber + 1;
    }
}
