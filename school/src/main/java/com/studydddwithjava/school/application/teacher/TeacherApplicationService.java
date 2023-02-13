package com.studydddwithjava.school.application.teacher;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.teacher.TeacherPw;
import com.studydddwithjava.school.domain.model.teacher.TeacherService;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherApplicationService {

    @Autowired
    @Qualifier("mysql")
    private ITeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    public boolean login(String firstName, String lastName, String pw) {
        UserName teacherName = new UserName(firstName, lastName);
        TeacherPw teacherPw = new TeacherPw(pw);

        Teacher teacher = new Teacher(teacherName, teacherPw);

        return this.teacherService.login(teacher) != null;
    }

    public Optional<TeacherData> findByUserName(String userName) {
        String[] nameArr = userName.split(" ", 2); // フルネームで取得するので、firstnameとlastnameに分割
        Optional<Teacher> optTeacher = teacherRepository.findByUserName(new UserName(nameArr[0], nameArr[1]));

        if (optTeacher.isEmpty()) return Optional.empty();

        Teacher teacher = optTeacher.get();

        return Optional.of(
                new TeacherData(
                        teacher.getName().getFirstName(),
                        teacher.getName().getLastName(),
                        teacher.getName().getFullName()
                )
        );
    }
}
