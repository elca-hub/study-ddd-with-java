package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembershipDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeacherTeamMembershipContext extends JpaRepository<TeacherTeamMembershipDataModel, String> {
    List<TeacherTeamMembershipDataModel> findByTeacherId(String teacherId);

    @Transactional
    void deleteByTeamId(String teamId);
}
