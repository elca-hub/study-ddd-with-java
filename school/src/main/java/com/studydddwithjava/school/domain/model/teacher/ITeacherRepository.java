package com.studydddwithjava.school.domain.model.teacher;

import com.studydddwithjava.school.domain.model.user.UserName;

import java.util.Optional;

public interface ITeacherRepository {
    Teacher findByUserNameAndPw(Teacher teacher);
    Optional<Teacher> findByUserName(UserName username);

    void save(Teacher teacher);
    void delete(Teacher teacher);

    void update(Teacher teacher);
}
