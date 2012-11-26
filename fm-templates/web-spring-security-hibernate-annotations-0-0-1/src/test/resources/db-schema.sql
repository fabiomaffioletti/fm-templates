SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `template` ;
CREATE SCHEMA IF NOT EXISTS `template` DEFAULT CHARACTER SET utf8 ;
USE `template` ;

-- -----------------------------------------------------
-- Table `template`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`country` ;

CREATE  TABLE IF NOT EXISTS `template`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NOT NULL ,
  `foundation` VARCHAR(45) NOT NULL ,
  `created_at` DATETIME NOT NULL ,
  `created_by` VARCHAR(45) NOT NULL ,
  `modified_at` DATETIME NOT NULL ,
  `modified_by` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `template`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`city` ;

CREATE  TABLE IF NOT EXISTS `template`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `created_at` DATETIME NOT NULL ,
  `created_by` VARCHAR(45) NOT NULL ,
  `modified_at` DATETIME NOT NULL ,
  `modified_by` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `template`.`country_city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`country_city` ;

CREATE  TABLE IF NOT EXISTS `template`.`country_city` (
  `country_id` INT NOT NULL ,
  `city_id` INT NOT NULL ,
  PRIMARY KEY (`country_id`, `city_id`) ,
  INDEX `fk_country_city_1` (`country_id` ASC) ,
  INDEX `fk_country_city_2` (`city_id` ASC) ,
  CONSTRAINT `fk_country_city_1`
    FOREIGN KEY (`country_id` )
    REFERENCES `template`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_country_city_2`
    FOREIGN KEY (`city_id` )
    REFERENCES `template`.`city` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `template`.`country_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`country_role` ;

CREATE  TABLE IF NOT EXISTS `template`.`country_role` (
  `country_id` INT NOT NULL ,
  `role_id` INT NOT NULL ,
  PRIMARY KEY (`country_id`, `role_id`) ,
  INDEX `fk_country_role_1` (`country_id` ASC) ,
  INDEX `fk_country_role_2` (`role_id` ASC) ,
  CONSTRAINT `fk_country_role_1`
    FOREIGN KEY (`country_id` )
    REFERENCES `template`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_country_role_2`
    FOREIGN KEY (`role_id` )
    REFERENCES `template`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `template`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`user` ;

CREATE  TABLE IF NOT EXISTS `template`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `enabled` CHAR(1) NOT NULL ,
  `account_expired` CHAR(1) NOT NULL ,
  `account_locked` CHAR(1) NOT NULL ,
  `credentials_expired` CHAR(1) NOT NULL ,
  `first_name` VARCHAR(100) NULL ,
  `last_name` VARCHAR(100) NULL ,
  `login_ok` INT NOT NULL ,
  `last_login_ok` DATETIME NULL ,
  `login_ko` INT NOT NULL ,
  `last_login_ko` DATETIME NULL ,
  `last_login_ok_ip_address` VARCHAR(45) NULL ,
  `last_login_ko_ip_address` VARCHAR(45) NULL ,
  `street` VARCHAR(255) NULL ,
  `number`VARCHAR(45) NULL ,
  `city` VARCHAR(100) NULL ,
  `zip_code` INT NULL ,
  `state` VARCHAR(100) NULL ,
  `country` VARCHAR(100) NULL ,
  `created_at` DATETIME NOT NULL ,
  `created_by` VARCHAR(45) NOT NULL ,
  `modified_at` DATETIME NOT NULL ,
  `modified_by` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `template`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`role` ;

CREATE  TABLE IF NOT EXISTS `template`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  `role_description` VARCHAR(45) NULL DEFAULT NULL ,
  `created_at` DATETIME NOT NULL ,
  `created_by` VARCHAR(45) NOT NULL ,
  `modified_at` DATETIME NOT NULL ,
  `modified_by` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `template`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`user_role` ;

CREATE  TABLE IF NOT EXISTS `template`.`user_role` (
  `user_id` INT NOT NULL ,
  `role_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `role_id`) ,
  INDEX `fk_user_role_1` (`user_id` ASC) ,
  INDEX `fk_user_role_2` (`role_id` ASC) ,
  CONSTRAINT `fk_user_role_1`
    FOREIGN KEY (`user_id` )
    REFERENCES `template`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_2`
    FOREIGN KEY (`role_id` )
    REFERENCES `template`.`role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `template`.`config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`config` ;

CREATE  TABLE IF NOT EXISTS `template`.`config` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `config_name` VARCHAR(45) NOT NULL ,
  `config_value` VARCHAR(255) NOT NULL ,
  `created_at` DATETIME NOT NULL ,
  `created_by` VARCHAR(45) NOT NULL ,
  `modified_at` DATETIME NOT NULL ,
  `modified_by` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `IDX_CONFIG_NAME` (`config_name` ASC) )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
