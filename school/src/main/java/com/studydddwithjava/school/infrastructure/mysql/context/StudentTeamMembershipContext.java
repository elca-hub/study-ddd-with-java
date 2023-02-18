package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.StudentTeamMembershipDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentTeamMembershipContext extends JpaRepository<StudentTeamMembershipDataModel, String> {
    List<StudentTeamMembershipDataModel> findByTeamId(String teamId);
}
