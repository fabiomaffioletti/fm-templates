SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `template` ;
CREATE SCHEMA IF NOT EXISTS `template` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `template` ;

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
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `template`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`role` ;

CREATE  TABLE IF NOT EXISTS `template`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  `role_description` VARCHAR(45) NULL ,
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



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
