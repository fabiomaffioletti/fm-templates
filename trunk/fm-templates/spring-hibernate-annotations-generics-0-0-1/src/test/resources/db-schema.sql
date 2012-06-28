SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `template` ;
CREATE SCHEMA IF NOT EXISTS `template` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `template` ;

-- -----------------------------------------------------
-- Table `template`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`country` ;

CREATE  TABLE IF NOT EXISTS `template`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `template`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `template`.`city` ;

CREATE  TABLE IF NOT EXISTS `template`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `country` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_city_country` (`country` ASC) ,
  CONSTRAINT `fk_city_country`
    FOREIGN KEY (`country` )
    REFERENCES `template`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
