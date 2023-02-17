package com.studydddwithjava.school.infrastructure.mysql.context;

import com.studydddwithjava.school.infrastructure.mysql.entity.TeamDataModel;
import com.studydddwithjava.school.infrastructure.mysql.entity.UsingTeamDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsingTeamContext extends JpaRepository<UsingTeamDataModel, String> {
}
