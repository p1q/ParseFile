package com.parsefile.dao.jdbc;

import com.parsefile.dao.StatisticsDao;
import com.parsefile.model.Statistics;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticsDaoJdbcImpl implements StatisticsDao {
    private static final Logger LOGGER = Logger.getLogger(StatisticsDaoJdbcImpl.class);

    private Connection connection;

    public StatisticsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addLine(String line) {
        String query = "INSERT INTO `lines` (content) VALUES (?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, line);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void addStatistics(Statistics statistics) {
        String query = "INSERT INTO statistics (longest_word, shortest_word, line_length," +
                " words_quantity, average_word_length, non_space_symbol_quantity)" +
                " VALUES (?, ?, ?, ?, ?, ?);";
        //long userRoleId = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, statistics.getLongestWord());
            preparedStatement.setString(2, statistics.getShortestWord());
            preparedStatement.setInt(3, statistics.getLineLength());
            preparedStatement.setInt(4, statistics.getWordsQuantity());
            preparedStatement.setInt(5, statistics.getAverageWordLength());
            preparedStatement.setInt(6, statistics.getNonSpaceSymbolQuantity());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Failed to add the statistics.");
                throw new SQLException("Failed to add the statistics.");
            }
/*
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getLong(1));
                } else {
                    LOGGER.error("Error obtaining user ID.");
                    throw new SQLException("Error obtaining user ID.");
                }
            }

            query = "SELECT role_id FROM roles WHERE name ='USER'";
            ResultSet resultSet = preparedStatement.executeQuery(query);
            resultSet.next();
            userRoleId = resultSet.getLong("role_id");
            user.addRole(new Role(userRoleId, "USER"));
*/
        } catch (SQLException e) {
            LOGGER.error(e);
        }
/*
        query = "INSERT INTO users_roles (user_id, role_id) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUserId().toString());
            preparedStatement.setString(2, Long.toString(userRoleId));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        */

    }

}
