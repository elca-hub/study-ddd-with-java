package com.studydddwithjava.school.application.student;

import com.studydddwithjava.school.application.student.param.StudentRegisterParam;
import com.studydddwithjava.school.domain.model.student.IStudentRepository;
import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.teacher.ITeacherRepository;
import com.studydddwithjava.school.domain.model.teacher.Teacher;
import com.studydddwithjava.school.domain.model.team.ITeamRepository;
import com.studydddwithjava.school.domain.model.team.Team;
import com.studydddwithjava.school.domain.model.user.UserName;
import com.studydddwithjava.school.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentApplicationService {
    @Autowired
    @Qualifier("mysql-team")
    private ITeamRepository teamRepository;

    @Autowired
    @Qualifier("mysql-student")
    private IStudentRepository studentRepository;

    @Autowired
    @Qualifier("mysql-teacher")
    private ITeacherRepository teacherRepository;

    @Autowired
    private StudentService studentService;

    public void register(StudentRegisterParam studentRegisterParam, String teacherName) {
        Optional<Team> optionalTeam = teamRepository.findById(studentRegisterParam.getTeamId());
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserName(new UserName(teacherName));

        if (optionalTeam.isEmpty() || optionalTeacher.isEmpty()) {
            throw new IllegalStateException("The id could not be retrieved correctly.");
        }

        Team team = optionalTeam.get();
        Teacher teacher = optionalTeacher.get();

        Student student = new Student(
                new UserName(studentRegisterParam.getFirstname(), studentRegisterParam.getLastname()),
                studentRegisterParam.getStudentNumber(),
                team,
                teacher
        );

        if (studentService.isExistInTeam(student)) {
            throw new IllegalArgumentException(String.format(
                    "A student with that name cannot be registered because he/she already exists in the team.\n Student name: %s",
                    student.getName().getFullName()));
        }

        if (studentRegisterParam.isAutoInc()) {
            int autoStudentNumber = studentService.makeStudentNumber(team);
            student.changeStudentNumber(autoStudentNumber);
        } else if (studentService.isExistStudentNumberInTeam(student, team)){
            throw new IllegalArgumentException(String.format(
                    "That student number is already in use and cannot be used.\n Student number: %d",
                    student.getStudentNumber()));
        }
        studentRepository.save(student);
    }

    public List<StudentData> fetchStudents(String teacherName) {
        Optional<Teacher> optionalTeacher = teacherRepository.findByUserName(new UserName(teacherName));

        if (optionalTeacher.isEmpty()) throw new IllegalStateException("You are not logged in correctly.");

        Teacher teacher = optionalTeacher.get();

        List<Student> students = studentRepository.findAll();

        return students.stream().map(StudentData::new).toList();
    }

    public Optional<StudentData> findById(String studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);

        return optionalStudent.map(StudentData::new);
    }
}
