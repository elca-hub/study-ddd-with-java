package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.StudentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentContext extends JpaRepository<StudentDataModel, String> {
}
