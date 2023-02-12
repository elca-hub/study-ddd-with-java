package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.TeacherDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherContext extends JpaRepository<TeacherDataModel, String> {
    List<TeacherDataModel> findByFirstnameAndLastnameAndPw(String firstname, String lastname, String pw);
}
