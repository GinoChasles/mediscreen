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
CREATE TABLE IF NOT EXISTS `mediscreen_patients`.`patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `birthdate` DATE NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `address` VARCHAR(255) NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into patient (firstname, lastname, birthdate, gender, address, phone) values ("TestNone", "Test", "1966-12-31", "F", "1 Brookside St",  "100-222-3333");