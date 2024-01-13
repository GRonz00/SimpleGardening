-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema simplegardening
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema simplegardening
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `simplegardening` DEFAULT CHARACTER SET utf8 ;
USE `simplegardening` ;

-- -----------------------------------------------------
-- Table `simplegardening`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simplegardening`.`User` ;

CREATE TABLE IF NOT EXISTS `simplegardening`.`User` (
    `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    `type` ENUM('client', 'pro') NOT NULL,
    `lon` VARCHAR(45) NOT NULL,
    `lat` VARCHAR(45) NOT NULL,
    `address` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`username`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simplegardening`.`Plant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simplegardening`.`Plant` ;

CREATE TABLE IF NOT EXISTS `simplegardening`.`Plant` (
    `name` VARCHAR(45) NOT NULL,
    `type` ENUM('indoor', 'outdoor') NOT NULL,
    `size` ENUM('small', 'medium', 'large') NOT NULL,
    `Image` MEDIUMBLOB NULL,
    `client` VARCHAR(45) NOT NULL,
    `state` VARCHAR(45) NULL,
    PRIMARY KEY (`name`, `client`),
    INDEX `fk_Plant_User1_idx` (`client` ASC) VISIBLE,
    CONSTRAINT `fk_Plant_User1`
    FOREIGN KEY (`client`)
    REFERENCES `simplegardening`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simplegardening`.`Reminder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simplegardening`.`Reminder` ;

CREATE TABLE IF NOT EXISTS `simplegardening`.`Reminder` (
                                                            `type` ENUM('water', 'nebulize', 'fertilize') NOT NULL,
    `when` DATETIME NOT NULL,
    `client` VARCHAR(45) NOT NULL,
    `plant` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`type`, `client`, `plant`),
    INDEX `fk_Reminder_Plant1_idx` (`client` ASC, `plant` ASC) VISIBLE,
    CONSTRAINT `fk_Reminder_Plant1`
    FOREIGN KEY (`client` , `plant`)
    REFERENCES `simplegardening`.`Plant` (`client` , `name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simplegardening`.`RequestForm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simplegardening`.`RequestForm` ;

CREATE TABLE IF NOT EXISTS `simplegardening`.`RequestForm` (
                                                               `idrequestForm` INT NOT NULL AUTO_INCREMENT,
                                                               `start` DATE NOT NULL,
                                                               `end` DATE NOT NULL,
                                                               `basePrice` FLOAT NOT NULL,
                                                               `extraPrice` INT NULL,
                                                               `pickUpAvi` TINYINT NOT NULL,
                                                               `pickUpBasePrice` FLOAT NULL,
                                                               `pickUpKMPrice` FLOAT NULL,
                                                               `newDiscount` TINYINT NOT NULL,
                                                               `pro` VARCHAR(45) NOT NULL,
    `availability` INT NOT NULL,
    `kmMax` INT NULL,
    `typePlant` VARCHAR(45) NOT NULL,
    `sizePlant` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idrequestForm`),
    INDEX `fk_requestForm_User1_idx` (`pro` ASC) VISIBLE,
    CONSTRAINT `fk_requestForm_User1`
    FOREIGN KEY (`pro`)
    REFERENCES `simplegardening`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simplegardening`.`Request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simplegardening`.`Request` ;

CREATE TABLE IF NOT EXISTS `simplegardening`.`Request` (
                                                           `idRequest` INT NOT NULL AUTO_INCREMENT,
                                                           `price` FLOAT NOT NULL,
                                                           `start` DATE NOT NULL,
                                                           `end` DATE NOT NULL,
                                                           `pro` VARCHAR(45) NOT NULL,
    `Plant_client` VARCHAR(45) NOT NULL,
    `Plant_name` VARCHAR(45) NOT NULL,
    `state` ENUM('sent', 'accepted', 'rejected') NULL,
    `pickup` TINYINT NULL,
    `RequestForm_idrequestForm` INT NOT NULL,
    PRIMARY KEY (`idRequest`, `RequestForm_idrequestForm`),
    INDEX `fk_Request_User1_idx` (`pro` ASC) VISIBLE,
    INDEX `fk_Request_Plant1_idx` (`Plant_client` ASC, `Plant_name` ASC) VISIBLE,
    INDEX `fk_Request_RequestForm1_idx` (`RequestForm_idrequestForm` ASC) VISIBLE,
    CONSTRAINT `fk_Request_User1`
    FOREIGN KEY (`pro`)
    REFERENCES `simplegardening`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Request_Plant1`
    FOREIGN KEY (`Plant_client` , `Plant_name`)
    REFERENCES `simplegardening`.`Plant` (`client` , `name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Request_RequestForm1`
    FOREIGN KEY (`RequestForm_idrequestForm`)
    REFERENCES `simplegardening`.`RequestForm` (`idrequestForm`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `simplegardening`.`Chat`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `simplegardening`.`Chat` ;

CREATE TABLE IF NOT EXISTS `simplegardening`.`Chat` (
                                                        `nMessage` INT NOT NULL AUTO_INCREMENT,
                                                        `User_username` VARCHAR(45) NOT NULL,
    `User_username1` VARCHAR(45) NOT NULL,
    `text` TEXT NOT NULL,
    PRIMARY KEY (`nMessage`, `User_username`, `User_username1`),
    INDEX `fk_chat_User1_idx` (`User_username` ASC) VISIBLE,
    INDEX `fk_chat_User2_idx` (`User_username1` ASC) VISIBLE,
    CONSTRAINT `fk_chat_User1`
    FOREIGN KEY (`User_username`)
    REFERENCES `simplegardening`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_chat_User2`
    FOREIGN KEY (`User_username1`)
    REFERENCES `simplegardening`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
