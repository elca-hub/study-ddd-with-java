package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.StudentTeamMembershipDataModel;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentTeamMembershipContext extends JpaRepository<StudentTeamMembershipDataModel, String> {
    List<StudentTeamMembershipDataModel> findByTeamId(String teamId);

    @Query(value = "SELECT MAX(student_number) max FROM student_team_membership WHERE team_id = ?1 GROUP BY team_id", nativeQuery = true)
    Optional<Tuple> findMaxStudentNumber(String teamId);

    @Query("select (count(s) > 0) from StudentTeamMembershipDataModel s where s.studentNumber = ?1 and s.teamId = ?2")
    boolean existsByStudentNumberAndTeamId(int studentNumber, String teamId);
}
