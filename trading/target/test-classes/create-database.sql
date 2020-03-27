-- MySQL Script generated by MySQL Workbench
-- Fri Mar  6 18:54:28 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db` ;

-- -----------------------------------------------------
-- Schema db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db` DEFAULT CHARACTER SET utf8 ;
USE `db` ;

-- -----------------------------------------------------
-- Table `db`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`users` ;

CREATE TABLE IF NOT EXISTS `db`.`users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `currentBalance` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`users_roles`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `db`.`users_roles` ;
--
-- CREATE TABLE IF NOT EXISTS `db`.`users_roles` (
--   `roleId` INT NOT NULL AUTO_INCREMENT,
--   `roleType` ENUM("USER") NOT NULL,
--   `userId` INT NOT NULL,
--   PRIMARY KEY (`roleId`, `userId`),
--   INDEX `fk_users_roles_users_idx` (`userId` ASC) VISIBLE,
--   CONSTRAINT `fk_users_roles_users`
--     FOREIGN KEY (`userId`)
--     REFERENCES `db`.`users` (`userId`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`stocks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`stocks` ;

CREATE TABLE IF NOT EXISTS `db`.`stocks` (
  `stockId` INT NOT NULL AUTO_INCREMENT,
  `symbol` VARCHAR(45) NOT NULL,
  `price` DECIMAL(9,2) NOT NULL,
  `ownedUnits` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`stockId`, `userId`),
  INDEX `fk_stocks_users1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_stocks_users1`
    FOREIGN KEY (`userId`)
    REFERENCES `db`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db`.`transactions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db`.`transactions` ;

CREATE TABLE IF NOT EXISTS `db`.`transactions` (
  `transactionId` INT NOT NULL AUTO_INCREMENT,
  `amountTraded` INT NULL,
  `symbol` VARCHAR(45) NULL,
  `transactionDateTime` DATETIME NULL,
  `stockPriceAtPurchase` DECIMAL(9,2) NULL,
  `transactionType` ENUM("BUY", "SELL") NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`transactionId`, `userId`),
  INDEX `fk_transactions_users1_idx` (`userId` ASC) VISIBLE,
  CONSTRAINT `fk_transactions_users1`
    FOREIGN KEY (`userId`)
    REFERENCES `db`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
