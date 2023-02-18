package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.TeacherTeamMembershipDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentTeamMembershipContext extends JpaRepository<TeacherTeamMembershipDataModel, String> {
}
