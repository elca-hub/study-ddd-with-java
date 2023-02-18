CREATE DATABASE IF NOT EXISTS school_management;

use school_management;
CREATE TABLE IF NOT EXISTS teachers(
	id varchar(255) NOT NULL,
	pw varchar(255) NOT NULL,
	firstname varchar(25) NOT NULL,
	lastname varchar(25) NOT NULL,
	username varchar(50) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8mb4;

CREATE TABLE IF NOT EXISTS students (
	id varchar(255) NOT NULL,
	firstname varchar(25) NOT NULL,
	lastname varchar(25) NOT NULL,
	username varchar(100) NOT NULL,
	student_number int NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY (student_number)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS teams (
	id varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT groups_PK PRIMARY KEY (id),
	CONSTRAINT groups_UN UNIQUE KEY (name)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS teacher_team_membership (
	id int auto_increment NOT NULL,
	teacher_id varchar(255) NOT NULL,
	team_id varchar(255) NOT NULL,
	PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4;
