package com.parsefile.dao.jdbc;

import com.parsefile.dao.StatisticsDao;
import com.parsefile.model.Statistics;
import org.apache.log4j.Logger;

import java.sql.Connection;

public class StatisticsDaoJdbcImpl extends AbstractDao<Statistics> implements StatisticsDao {
        private static final Logger LOGGER = Logger.getLogger(StatisticsDaoJdbcImpl.class);

    public StatisticsDaoJdbcImpl(Connection connection) {
        super(connection);
    }
}
