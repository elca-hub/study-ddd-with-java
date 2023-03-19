package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.StudentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentContext extends JpaRepository<StudentDataModel, String> {
    List<StudentDataModel> findByIdIn(List<String> studentIds);

    List<StudentDataModel> findByTeacherId(String teacherId);
}
