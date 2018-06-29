-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sql7234875
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sql7234875
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sql7234875` DEFAULT CHARACTER SET utf8 ;
USE `sql7234875` ;

-- -----------------------------------------------------
-- Table `sql7234875`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  `first_name` VARCHAR(25) NOT NULL,
  `last_name` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sql7234875`.`estimations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`estimations` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `estimation` INT(11) NOT NULL,
  `remaining` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 48
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sql7234875`.`priorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`priorities` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `title_UNIQUE` ON `sql7234875`.`priorities` (`title` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`statuses` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `title_UNIQUE` ON `sql7234875`.`statuses` (`title` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`taskMongos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`taskMongos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `parent_id` BIGINT(20) NULL DEFAULT NULL,
  `estimation_id` BIGINT(20) NOT NULL,
  `owner_id` BIGINT(20) NOT NULL,
  `worker_id` BIGINT(20) NOT NULL,
  `priority_id` TINYINT(4) NOT NULL,
  `status_id` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tasks_users1`
    FOREIGN KEY (`owner_id`)
    REFERENCES `sql7234875`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_users2`
    FOREIGN KEY (`worker_id`)
    REFERENCES `sql7234875`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_estimations1`
    FOREIGN KEY (`estimation_id`)
    REFERENCES `sql7234875`.`estimations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_priorities1`
    FOREIGN KEY (`priority_id`)
    REFERENCES `sql7234875`.`priorities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_statuses1`
    FOREIGN KEY (`status_id`)
    REFERENCES `sql7234875`.`statuses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_tasks1`
    FOREIGN KEY (`parent_id`)
    REFERENCES `sql7234875`.`taskMongos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 27
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_tasks_estimations1_idx` ON `sql7234875`.`taskMongos` (`estimation_id` ASC);

CREATE INDEX `fk_tasks_priorities1_idx` ON `sql7234875`.`taskMongos` (`priority_id` ASC);

CREATE INDEX `fk_tasks_statuses1_idx` ON `sql7234875`.`taskMongos` (`status_id` ASC);

CREATE INDEX `fk_tasks_tasks1_idx` ON `sql7234875`.`taskMongos` (`parent_id` ASC);

CREATE INDEX `fk_tasks_users1_idx` ON `sql7234875`.`taskMongos` (`owner_id` ASC);

CREATE INDEX `fk_tasks_users2_idx` ON `sql7234875`.`taskMongos` (`worker_id` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`comments` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `text` TEXT NOT NULL,
  `author_id` BIGINT(20) NOT NULL,
  `task_id` BIGINT(20) NOT NULL,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comments_users1`
    FOREIGN KEY (`author_id`)
    REFERENCES `sql7234875`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_tasks1`
    FOREIGN KEY (`task_id`)
    REFERENCES `sql7234875`.`taskMongos` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_comments_tasks1_idx1` ON `sql7234875`.`comments` (`task_id` ASC);

CREATE INDEX `fk_comments_users1_idx` ON `sql7234875`.`comments` (`author_id` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`log_works`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`log_works` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NOT NULL,
  `logged` INT(11) NOT NULL,
  `author_id` BIGINT(20) NOT NULL,
  `estimation_id` BIGINT(20) NOT NULL,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`, `estimation_id`),
  CONSTRAINT `fk_log_works_users1`
    FOREIGN KEY (`author_id`)
    REFERENCES `sql7234875`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_works_estimations1`
    FOREIGN KEY (`estimation_id`)
    REFERENCES `sql7234875`.`estimations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_log_works_estimations1_idx` ON `sql7234875`.`log_works` (`estimation_id` ASC);

CREATE INDEX `fk_log_works_users1_idx` ON `sql7234875`.`log_works` (`author_id` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`specializations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`specializations` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8;

CREATE UNIQUE INDEX `title_UNIQUE` ON `sql7234875`.`specializations` (`title` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`users_has_specializations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`users_has_specializations` (
  `user_id` BIGINT(20) NOT NULL,
  `specialization_id` TINYINT(4) NOT NULL,
  PRIMARY KEY (`user_id`, `specialization_id`),
  CONSTRAINT `fk_users_has_specializations_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sql7234875`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_specializations_specializations1`
    FOREIGN KEY (`specialization_id`)
    REFERENCES `sql7234875`.`specializations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `fk_users_has_specializations_specializations1_idx` ON `sql7234875`.`users_has_specializations` (`specialization_id` ASC);

CREATE INDEX `fk_users_has_specializations_users1_idx` ON `sql7234875`.`users_has_specializations` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `sql7234875`.`users_trello`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sql7234875`.`users_trello` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(25) NOT NULL,
  `access_key` VARCHAR(100) NOT NULL,
  `access_token` VARCHAR(100) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `users_trello_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sql7234875`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

CREATE INDEX `user_id` ON `sql7234875`.`users_trello` (`user_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
