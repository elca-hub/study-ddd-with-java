package com.studydddwithjava.school.infrastructure.mysql.repository;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.teacher.TeacherHashPw;
import com.studydddwithjava.school.domain.model.user.UserName;
import com.studydddwithjava.school.infrastructure.mysql.context.TeacherContext;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeacherDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("mysql-teacher")
public class TeacherRepository implements ITeacherRepository {
    @Autowired
    private TeacherContext teacherContext;

    @Override
    public Teacher findByUserNameAndPw(Teacher teacher) {
        if (teacher.getPw().isEmpty()) throw new IllegalStateException();

        List<TeacherDataModel> models = teacherContext.findByFirstnameAndLastnameAndPw(
                teacher.getName().getFirstName(),
                teacher.getName().getLastName(),
                teacher.getPw().get().getPw()
        );

        if (models.size() >= 2) throw new IllegalStateException("Duplicate password and teacher name.");

        if (models.size() == 0) {
            return null;
        }

        TeacherDataModel model = models.get(0);

        return new Teacher(
                model.id,
                new UserName(model.firstname, model.lastname),
                new TeacherHashPw(model.pw)
        );
    }

    @Override
    public Optional<Teacher> findByUserName(UserName username) {
        List<TeacherDataModel> models = teacherContext.findByFirstnameAndLastname(username.getFirstName(), username.getLastName());

        if (models.size() >= 2) throw new IllegalStateException("Duplicate password and teacher name.");

        if (models.size() == 0) {
            return Optional.empty();
        }

        TeacherDataModel model = models.get(0);

        return Optional.of(new Teacher(
                model.id,
                username,
                new TeacherHashPw(model.pw)
        ));
    }

    @Override
    @Transactional
    public void save(Teacher teacher) {
        TeacherDataModel model = new TeacherDataModel(teacher);
        teacherContext.save(model);
    }

    @Override
    @Transactional
    public void delete(Teacher teacher) {
        TeacherDataModel model = new TeacherDataModel(teacher);
        teacherContext.delete(model);
    }
}
