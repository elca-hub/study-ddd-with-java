package com.studydddwithjava.school.domain.model.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    @Qualifier("mysql")
    private ITeacherRepository teacherRepository;

    public Teacher login(Teacher teacher) {
        Teacher fetchModel = this.teacherRepository.findByUserNameAndPw(teacher);

        return fetchModel;
    }
}
