package com.studydddwithjava.school.infrastructure.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="groups")
public class GroupDataModel {
    @Id
    @Column
    public String id;

    @Column(length = 60)
    public String name;
}
