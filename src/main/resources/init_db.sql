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
    AUTO_INCREMENT = 2,
    AVG_ROW_LENGTH = 16384,
    CHARACTER SET utf8,
    COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS statistics (
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
                                            duplicate_id int(11) NOT NULL AUTO_INCREMENT,
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

CREATE USER 'parsefile'@'%' IDENTIFIED WITH mysql_native_password;
GRANT USAGE ON *.* TO 'parsefile'@'%'
    REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
SET PASSWORD FOR 'parsefile'@'%' = 'LujCMJd75Cx5C4qS';GRANT ALL PRIVILEGES ON `parsefile`.* TO 'parsefile'@'%';