SET NAMES 'utf8';

DROP DATABASE IF EXISTS parsefile;

CREATE DATABASE IF NOT EXISTS parsefile
    CHARACTER SET utf8
    COLLATE utf8_unicode_ci;

USE parsefile;

CREATE TABLE IF NOT EXISTS `lines` (
                                       line_id int(11) NOT NULL AUTO_INCREMENT,
                                       content longtext DEFAULT NULL,
                                       PRIMARY KEY (line_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 84,
    AVG_ROW_LENGTH = 16384,
    CHARACTER SET utf8,
    COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `statistics-of-line` (
                                                    statistics_id int(11) NOT NULL AUTO_INCREMENT,
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

CREATE TABLE IF NOT EXISTS `lines_statistics-of-line` (
                                                          line_id int(11) NOT NULL,
                                                          `statistics-of-line_id` int(11) NOT NULL,
                                                          PRIMARY KEY (line_id, `statistics-of-line_id`)
)
    ENGINE = INNODB,
    CHARACTER SET utf8,
    COLLATE utf8_unicode_ci;

ALTER TABLE `lines_statistics-of-line`
    ADD INDEX `statistics-of-line_fk_idx` (`statistics-of-line_id`);

ALTER TABLE `lines_statistics-of-line`
    ADD CONSTRAINT `statistics-of-line_fk` FOREIGN KEY (`statistics-of-line_id`)
        REFERENCES `statistics-of-line` (statistics_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `lines_statistics-of-line`
    ADD CONSTRAINT line_fk FOREIGN KEY (line_id)
        REFERENCES `lines` (line_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS `duplicates-in-line` (
                                                    duplicate_id int(11) NOT NULL AUTO_INCREMENT,
                                                    duplicate varchar(128) DEFAULT NULL,
                                                    quantity int(11) DEFAULT NULL,
                                                    line_id int(11) NOT NULL,
                                                    PRIMARY KEY (duplicate_id, line_id)
)
    ENGINE = INNODB,
    CHARACTER SET utf8,
    COLLATE utf8_unicode_ci;

ALTER TABLE `duplicates-in-line`
    ADD INDEX `statistics-duplicates_idx` (duplicate_id);

ALTER TABLE `duplicates-in-line`
    ADD CONSTRAINT `statistics-duplicates_fk` FOREIGN KEY (duplicate_id)
        REFERENCES `statistics-of-line` (statistics_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

CREATE TABLE IF NOT EXISTS `statistics-of-file` (
                                                    statistics_id int(11) NOT NULL AUTO_INCREMENT,
                                                    longest_word varchar(128) DEFAULT NULL,
                                                    shortest_word varchar(128) DEFAULT NULL,
                                                    line_length int(11) DEFAULT NULL,
                                                    words_quantity int(11) DEFAULT NULL,
                                                    average_word_length int(11) DEFAULT NULL,
                                                    non_space_symbol_quantity int(11) DEFAULT NULL,
                                                    PRIMARY KEY (statistics_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 3,
    CHARACTER SET utf8,
    COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `duplicates-in-file` (
                                                    duplicate_id int(11) NOT NULL AUTO_INCREMENT,
                                                    duplicate varchar(128) DEFAULT NULL,
                                                    quantity int(11) DEFAULT NULL,
                                                    PRIMARY KEY (duplicate_id)
)
    ENGINE = INNODB,
    AUTO_INCREMENT = 8,
    CHARACTER SET utf8,
    COLLATE utf8_unicode_ci;

ALTER TABLE `duplicates-in-file`
    ADD INDEX `statistics-duplicates_idx` (duplicate_id);