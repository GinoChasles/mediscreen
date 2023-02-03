-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydbpatients
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mediscreen_patients` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema mediscreen_patients
-- -----------------------------------------------------
USE `mediscreen_patients` ;

-- -----------------------------------------------------
-- Table `mediscreen_patients`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mediscreen_patients`.`patient`
(
    `id`
    INT
    NOT
    NULL
    AUTO_INCREMENT,
    `firstname`
    VARCHAR
(
    45
) NOT NULL,
    `lastname` VARCHAR
(
    45
) NOT NULL,
    `birthdate` DATE NOT NULL,
    `gender` VARCHAR
(
    1
) NOT NULL,
    `address` VARCHAR
(
    255
) NULL,
    `phone` VARCHAR
(
    45
) NULL,
    PRIMARY KEY
(
    `id`
),
    UNIQUE INDEX `id_UNIQUE`
(
    `id` ASC
) VISIBLE)
    ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE =utf8mb4_0900_ai_ci;


SET
SQL_MODE=@OLD_SQL_MODE;
SET
FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET
UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Ferguson", "Lucas", "1968-06-22", "M", "2 Warren Street", "387-866-1399");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Rees", "Pippa", "1952-09-27", "F", "745 West Valley Farms Drive", "	628-423-0993");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Arnold", "Edward", "1952-11-11", "M", "599 East Garden Ave", "123-727-2779");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Sharp", "Anthony", "1946-11-26", "M", "894 Hall Street", "451-761-8383");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Ince", "Wendy", "1958-06-29", "F", "4 Southampton Road", "802-911-9975");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Ross", "Tracey", "1949-12-07", "F", "40 Sulphur Springs Dr", "131-396-5049");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Wilson", "Claire", "1966-12-31", "F", "12 Cobblestone St", "300-452-1091");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Buckland", "Max", "1945-06-24", "M", "193 Vale St", "833-534-0864");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Clark", "Natalie", "1964-06-18", "F", "12 Beechwood Road", "241-467-9197");
insert into patient (firstname, lastname, birthdate, gender, address, phone)
values ("Bailey", "Piers", "1959-06-28", "M", "1202 Bumble Dr", "747-815-0557");
