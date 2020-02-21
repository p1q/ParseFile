/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

SET NAMES 'utf8';

DROP DATABASE IF EXISTS sql3324004;

CREATE DATABASE IF NOT EXISTS sql3324004
CHARACTER SET utf8
COLLATE utf8_unicode_ci;

USE sql3324004;

CREATE TABLE IF NOT EXISTS `lines` (
  line_id int(11) NOT NULL,
  content longtext DEFAULT NULL,
  PRIMARY KEY (line_id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS statistics (
  statistics_id int(11) NOT NULL,
  longest_word varchar(128) DEFAULT NULL,
  shortest_word varchar(128) DEFAULT NULL,
  line_length int(11) DEFAULT NULL,
  words_quantity int(11) DEFAULT NULL,
  average_word_length int(11) DEFAULT NULL,
  non_space_symbol_quantity int(11) DEFAULT NULL,
  PRIMARY KEY (statistics_id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS lines_statistics (
  line_id int(11) NOT NULL,
  statistics_id int(11) NOT NULL,
  PRIMARY KEY (line_id, statistics_id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

ALTER TABLE lines_statistics
ADD INDEX statistics_fk_idx (statistics_id);

ALTER TABLE lines_statistics
ADD CONSTRAINT line_fk FOREIGN KEY (line_id)
REFERENCES `lines` (line_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE lines_statistics
ADD CONSTRAINT statistics_fk FOREIGN KEY (statistics_id)
REFERENCES statistics (statistics_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS duplications (
  duplicate_id int(11) NOT NULL,
  duplicate varchar(128) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  line_id int(11) NOT NULL,
  PRIMARY KEY (duplicate_id, line_id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_unicode_ci;

ALTER TABLE duplications
ADD INDEX `statistics-duplicates_idx` (duplicate_id);

ALTER TABLE duplications
ADD CONSTRAINT `statistics-duplicates_fk` FOREIGN KEY (duplicate_id)
REFERENCES statistics (statistics_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;