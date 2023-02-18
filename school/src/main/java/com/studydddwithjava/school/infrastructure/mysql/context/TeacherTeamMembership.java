package com.studydddwithjava.school.infrastructure.mysql.context;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherTeamMembership extends JpaRepository<com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembership, String> {
    List<com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembership> findByTeacherId(String teacherId);
}
