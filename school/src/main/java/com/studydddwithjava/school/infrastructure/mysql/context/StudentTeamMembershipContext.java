package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.StudentTeamMembershipDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentTeamMembershipContext extends JpaRepository<StudentTeamMembershipDataModel, Integer> {
    List<StudentTeamMembershipDataModel> findByTeamId(String teamId);

    @Query(value = "SELECT MAX(student_number) max FROM student_team_membership WHERE team_id = ?1 GROUP BY team_id", nativeQuery = true)
    Optional<Tuple> findMaxStudentNumber(String teamId);
    boolean existsByStudentNumberAndTeamId(int studentNumber, String teamId);

    Optional<StudentTeamMembershipDataModel> findByStudentIdAndTeamId(String studentId, String teamId);

    @Query("SELECT team FROM TeamDataModel team JOIN FETCH team.studentTeamMembershipDataModelList stm WHERE stm.studentId = ?1")
    List<TeamDataModel> findTeamByStudentId(String studentId);

    @Transactional
    void deleteByStudentId(String studentId);

    @Transactional
    void deleteByStudentIdAndTeamId(String studentId, String teamId);
}
