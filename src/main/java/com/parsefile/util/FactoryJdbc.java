package com.parsefile.util;

import com.parsefile.dao.StatisticsDao;
import com.parsefile.dao.jdbc.StatisticsDaoJdbcImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryJdbc {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_NAME = "sql3324004";
    private static final String DATABASE_URL = "jdbc:mysql://sql3.freemysqlhosting.net/"
            + DATABASE_NAME + "?allowMultiQueries=true";
    private static final String USER = "sql3324004";
    private static final String PASSWORD = "VDJpLcuK14";
    private static final Logger LOGGER = Logger.getLogger(FactoryJdbc.class);

    private static Connection connection;
    private static StatisticsDao statisticsDao;

    static {
        try {
            LOGGER.info("Connecting to the database...");
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            LOGGER.info("Connection to the database was established successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
            e.printStackTrace();
        }
    }

    public static StatisticsDao getStatisticsDao() {
        return statisticsDao != null ? statisticsDao :
                (statisticsDao = new StatisticsDaoJdbcImpl(connection));
    }
}
