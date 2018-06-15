CREATE TABLE IF NOT EXISTS `users` (
  `id` IDENTITY NOT NULL,
  `email` VARCHAR(40) NOT NULL UNIQUE,
  `password` VARCHAR(65) NOT NULL,
  `first_name` VARCHAR(25) NOT NULL,
  `last_name` VARCHAR(25) NOT NULL,
  `confirmed` BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY ('id')
);

CREATE TABLE IF NOT EXISTS `estimations` (
  `id` IDENTITY NOT NULL,
  `estimation` INT NOT NULL,
  `remaining` INT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `priorities` (
  `id` IDENTITY NOT NULL,
  `title` VARCHAR(15) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `statuses` (
  `id` IDENTITY NOT NULL,
  `title` VARCHAR(15) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` IDENTITY NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `parent_id` BIGINT NULL DEFAULT NULL,
  `estimation_id` BIGINT NOT NULL,
  `owner_id` BIGINT NOT NULL,
  `worker_id` BIGINT NOT NULL,
  `priority_id` BIGINT NOT NULL,
  `status_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tasks_users1`
  FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_users2`
  FOREIGN KEY (`worker_id`) REFERENCES `users` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_estimations1`
  FOREIGN KEY (`estimation_id`) REFERENCES `estimations` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_priorities1`
  FOREIGN KEY (`priority_id`) REFERENCES `priorities` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_statuses1`
  FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tasks_tasks1`
  FOREIGN KEY (`parent_id`) REFERENCES `tasks` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `comments` (
  `id` IDENTITY NOT NULL,
  `text` TEXT NOT NULL,
  `author_id` BIGINT NOT NULL,
  `task_id` BIGINT NOT NULL,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_comments_users1`
  FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_tasks1`
  FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `log_works` (
  `id` IDENTITY NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `logged` INT NOT NULL,
  `author_id` BIGINT NOT NULL,
  `estimation_id` BIGINT NOT NULL,
  `last_update` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_log_works_users1`
  FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_works_estimations1`
  FOREIGN KEY (`estimation_id`) REFERENCES `estimations` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `specializations` (
  `id` IDENTITY NOT NULL,
  `title` VARCHAR(25) NOT NULL UNIQUE ,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `users_has_specializations` (
  `user_id` BIGINT NOT NULL,
  `specialization_id` BIGINT NOT NULL,
  PRIMARY KEY (`user_id`, `specialization_id`),
  CONSTRAINT `fk_users_has_specializations_users1`
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_specializations_specializations1`
  FOREIGN KEY (`specialization_id`) REFERENCES `specializations` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `users_trello` (
  `id` IDENTITY NOT NULL,
  `username` VARCHAR(25) NOT NULL,
  `access_key` VARCHAR(100) NOT NULL,
  `access_token` VARCHAR(100) NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `users_trello_ibfk_1`
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);