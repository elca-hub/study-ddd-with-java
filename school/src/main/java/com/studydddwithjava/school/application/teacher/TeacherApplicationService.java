package com.studydddwithjava.school.application.teacher;

import com.studydddwithjava.school.application.teacher.param.TeacherUpdateParam;
import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.teacher.TeacherPw;
import com.studydddwithjava.school.domain.model.user.UserName;
import com.studydddwithjava.school.domain.service.TeacherService;
import com.studydddwithjava.school.infrastructure.security.LoginTeacherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherApplicationService {

    @Autowired
    @Qualifier("mysql-teacher")
    private ITeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    /**
     * 教員を新規に登録
     * @param firstName 姓
     * @param lastName 名
     * @param pw パスワード
     * @return 登録が完了したか
     */
    public boolean register(String firstName, String lastName, String pw) throws IllegalArgumentException {
        UserName teacherName = new UserName(firstName, lastName);
        Teacher teacher = new Teacher(teacherName, new TeacherPw(pw));

        if (teacherRepository.findByUserName(teacherName).isPresent()) return false;

        teacherRepository.save(teacher);

        return true;
    }

    public Optional<TeacherData> findByUserName(String userName) {
        String[] nameArr = userName.split(" ", 2); // フルネームで取得するので、firstnameとlastnameに分割
        Optional<Teacher> optTeacher = teacherRepository.findByUserName(new UserName(nameArr[0], nameArr[1]));

        if (optTeacher.isEmpty()) return Optional.empty();

        Teacher teacher = optTeacher.get();

        return Optional.of(new TeacherData(teacher));
    }

    public String fetchFullName(String firstname, String lastname) {
        UserName name = new UserName(firstname, lastname);

        return name.getFullName();
    }

    public boolean delete(String firstname, String lastname, String pw) {
        UserName username = new UserName(firstname, lastname);
        Optional<Teacher> find = teacherRepository.findByUserName(username);

        if (find.isPresent()) {
            Teacher teacher = find.get();

            boolean isMatch = teacher.getHashPw().match(new TeacherPw(pw));
            if (isMatch) teacherRepository.delete(teacher);

            return isMatch;
        }

        return false;
    }

    public void update(TeacherUpdateParam teacherUpdateParam, LoginTeacherDetails loginTeacherDetails) {
        var teacherName = new UserName(loginTeacherDetails.getUsername());
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserName(teacherName);

        if (optionalTeacher.isEmpty()) throw new IllegalStateException("The information of the currently logged-in user is not registered in the DB.");

        Teacher teacher = optionalTeacher.get();

        if (!teacher.getHashPw().match(new TeacherPw(teacherUpdateParam.getBeforePw()))) {
            throw new IllegalArgumentException("Password is different");
        }

        /* 更新前のパスワードが一致したら */
        teacher.changePw(new TeacherPw(teacherUpdateParam.getPw()));
        teacher.changeName(new UserName(teacherUpdateParam.getFirstname(), teacherUpdateParam.getLastname()));

        if (!teacher.getName().getFullName().equals(loginTeacherDetails.getUsername()) &&
                teacherService.isExistSameName(teacher)) {
            throw new IllegalArgumentException("A user with the same name already exists.");
        }

        teacherRepository.update(teacher);
    }
}
