package com.studydddwithjava.school.domain.model.teacher;

import com.studydddwithjava.school.domain.model.student.Student;
import com.studydddwithjava.school.domain.model.task.Task;
import com.studydddwithjava.school.domain.model.user.UserName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Teacher {
    private final String id;
    private UserName name;
    private TeacherPw pw;
    private final List<String> roleList = List.of("teacher");
    private TeacherHashPw hashPw;

    public Teacher(String id, UserName name, TeacherPw pw) {
        this.id = id;
        this.name = name;
        changePw(pw);
    }

    public Teacher(UserName name, TeacherPw pw) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        changePw(pw);
    }

    public Teacher(String id, UserName name, TeacherHashPw hashPw) {
        this.id = id;
        this.name = name;
        this.pw = null;
        this.hashPw = hashPw;
    }

    /**
     * 複数人の生徒に複数個のタスクを付与するメソッド
     * @param students 付与される生徒の配列
     * @param tasks 付与するタスクの配列
     */
    public void addTasks(Student[] students, Task[] tasks) {
        for (Student student : students) {
            for (Task task: tasks) student.addTask(task);
        }
    }

    public void changeName(UserName name) {
        this.name = name;
    }

    public void changePw(TeacherPw pw) {
        this.pw = pw;
        this.hashPw = new TeacherHashPw(pw);
    }

    public String getId() {
        return id;
    }

    public UserName getName() {
        return name;
    }

    public Optional<TeacherPw> getPw() {
        return Optional.ofNullable(pw);
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public TeacherHashPw getHashPw() {
        return hashPw;
    }
}
