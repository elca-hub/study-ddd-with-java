package com.studydddwithjava.school.application.teacher;

import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.teacher.TeacherHashPw;
import com.studydddwithjava.school.domain.model.teacher.TeacherPw;
import com.studydddwithjava.school.domain.service.TeacherService;
import com.studydddwithjava.school.domain.model.user.UserName;
import org.apache.catalina.User;
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

    /**
     * 教員を新規に登録
     * @param firstName 姓
     * @param lastName 名
     * @param pw パスワード
     * @return 登録が完了したか
     */
    public boolean register(String firstName, String lastName, String pw) {
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

        return Optional.of(
                new TeacherData(
                        teacher.getName().getFirstName(),
                        teacher.getName().getLastName(),
                        teacher.getName().getFullName()
                )
        );
    }

    public String fetchFullName(String firstname, String lastname) {
        UserName name = new UserName(firstname, lastname);

        return name.getFullName();
    }

    public void delete(String firstname, String lastname, String pw) {
        UserName username = new UserName(firstname, lastname);
        Optional<Teacher> find = teacherRepository.findByUserName(username);

        if (find.isPresent()) {
            Teacher teacher = find.get();

            boolean isMatch = teacher.getHashPw().match(new TeacherPw(pw));
            if (isMatch) teacherRepository.delete(teacher);
        }
    }
}
