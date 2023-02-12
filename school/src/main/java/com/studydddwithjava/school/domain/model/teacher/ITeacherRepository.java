package com.studydddwithjava.school.domain.model.teacher;

public interface ITeacherRepository {
    Teacher findByUserNameAndPw(Teacher teacher);
}
