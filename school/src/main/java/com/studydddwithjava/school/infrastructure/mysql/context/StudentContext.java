package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.StudentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentContext extends JpaRepository<StudentDataModel, String> {
    @Query("select s from StudentDataModel s where s.studentNumber = ?1")
    List<StudentDataModel> findByStudentNumber(int studentNumber);
    @Query("select s from StudentDataModel s order by s.studentNumber desc limit 1")
    Optional<StudentDataModel> findMaxStudentNumber();
}
