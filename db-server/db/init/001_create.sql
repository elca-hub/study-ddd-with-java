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
