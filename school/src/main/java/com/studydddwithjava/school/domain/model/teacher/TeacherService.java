package com.studydddwithjava.school.domain.model.teacher;

public class TeacherService {
    private final ITeacherRepository teacherRepository;

    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public boolean login(Teacher teacher) {
        return this.teacherRepository.isExist(teacher);
    }
}
