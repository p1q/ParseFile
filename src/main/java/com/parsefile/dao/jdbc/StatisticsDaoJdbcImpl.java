package com.parsefile.dao.jdbc;

import com.parsefile.dao.StatisticsDao;
import com.parsefile.model.Statistics;
import com.parsefile.model.WordDuplication;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticsDaoJdbcImpl implements StatisticsDao {
    private static final Logger LOGGER = Logger.getLogger(StatisticsDaoJdbcImpl.class);

    private Connection connection;

    public StatisticsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addLine(String line) {
        int lineId = 0;
        String query = "INSERT INTO `lines` (`content`) VALUES (?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, line);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Failed to add the line.");
                throw new SQLException("Failed to add the line.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    lineId = generatedKeys.getInt(1);
                } else {
                    LOGGER.error("Error obtaining line ID.");
                    throw new SQLException("Error obtaining line ID.");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return lineId;
    }

    @Override
    public void addLineStatistics(Statistics statistics, int lineId) {
        String query = "INSERT INTO `statistics-of-line` (`longest_word`, `shortest_word`,"
                + " `line_length`, `words_quantity`, `average_word_length`,"
                + " `non_space_symbol_quantity`, `line_id`) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, statistics.getLongestWord());
            preparedStatement.setString(2, statistics.getShortestWord());
            preparedStatement.setInt(3, statistics.getLineLength());
            preparedStatement.setInt(4, statistics.getWordsQuantity());
            preparedStatement.setInt(5, statistics.getAverageWordLength());
            preparedStatement.setInt(6, statistics.getNonSpaceSymbolQuantity());
            preparedStatement.setInt(7, lineId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Failed to add statistics.");
                throw new SQLException("Failed to add statistics.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        for (int i = 0; i < statistics.getWordDuplications().size(); i++) {
            addDuplicateLine(statistics.getWordDuplications().get(i), lineId);
        }
    }

    @Override
    public void addFileStatistics(Statistics statistics) {
        String query = "INSERT INTO `statistics-of-file` (`longest_word`, `shortest_word`,"
                + " `line_length`, `words_quantity`, `average_word_length`,"
                + " `non_space_symbol_quantity`) VALUES (?, ?, ?, ?, ?, ?);";

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
                LOGGER.error("Failed to add statistics.");
                throw new SQLException("Failed to add statistics.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        for (int i = 0; i < statistics.getWordDuplications().size(); i++) {
            addDuplicateFile(statistics.getWordDuplications().get(i));
        }
    }

    private void addDuplicateFile(WordDuplication duplicate) {
        String addDuplicatesQuery = "INSERT INTO `duplicates-in-file`"
                + " (`duplicate`, `quantity`) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(addDuplicatesQuery)) {
            preparedStatement.setString(1, duplicate.getDuplicate());
            preparedStatement.setInt(2, duplicate.getQuantity());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Failed to add the duplicate of whole file.");
                throw new SQLException("Failed to add the duplicate of whole file.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    private void addDuplicateLine(WordDuplication duplicate, int lineId) {
        String addDuplicatesQuery = "INSERT INTO `duplicates-in-line`"
                + " (`duplicate`, `quantity`, `line_id`) VALUES (?, ?, ?);";

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(addDuplicatesQuery)) {
            preparedStatement.setString(1, duplicate.getDuplicate());
            preparedStatement.setInt(2, duplicate.getQuantity());
            preparedStatement.setInt(3, lineId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.error("Failed to add the duplicate of whole file.");
                throw new SQLException("Failed to add the duplicate of whole file.");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
