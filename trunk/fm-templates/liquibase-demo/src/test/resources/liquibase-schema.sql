SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `liquibase_demo` ;
CREATE SCHEMA IF NOT EXISTS `liquibase_demo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `liquibase_demo` ;

-- -----------------------------------------------------
-- Table `liquibase_demo`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `liquibase_demo`.`user` ;

CREATE  TABLE IF NOT EXISTS `liquibase_demo`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
