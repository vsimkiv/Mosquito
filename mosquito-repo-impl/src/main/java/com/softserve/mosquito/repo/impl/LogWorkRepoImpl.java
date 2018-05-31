package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.LogWorkRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogWorkRepoImpl implements LogWorkRepo {

    private static final Logger LOGGER = LogManager.getLogger(LogWorkRepoImpl.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_LOG_WORK =
            "INSERT INTO log_works (description, logged, author_id, estimation_id) VALUE (?,?,?,?);";
    private static final String UPDATE_LOG_WORK =
            "UPDATE log_works SET description=?, logged=? WHERE id=?;";
    private static final String DELETE_LOG_WORK =
            "DELETE FROM log_works WHERE id=?;";
    private static final String READ_LOG_WORK =
            "SELECT * FROM log_works WHERE id=?;";
    private static final String READ_ALL_LOG_WORKS =
            "SELECT * FROM log_works;";

    @Override
    public LogWork create(LogWork logWork) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LOG_WORK)) {
            preparedStatement.setString(1, logWork.getDescription());
            preparedStatement.setInt(2, logWork.getLogged());
            preparedStatement.setLong(3, logWork.getUserId());
            preparedStatement.setLong(4, logWork.getEstimationId());
            preparedStatement.execute();

            if (preparedStatement.executeUpdate() == 0)
                LOGGER.error("Creating log-work was failed");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating log-work was failed");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public LogWork read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_LOG_WORK)) {
            preparedStatement.setLong(1, id);
            List<LogWork> logWorks = parsData(preparedStatement.executeQuery());
            if (!logWorks.isEmpty())
                return logWorks.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public LogWork update(LogWork logWork) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOG_WORK)) {
            preparedStatement.setString(1, logWork.getDescription());
            preparedStatement.setInt(2, logWork.getLogged());
            preparedStatement.setLong(3, logWork.getId());
            if (preparedStatement.executeUpdate() > 0)
                return read(logWork.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return logWork;
    }

    @Override
    public void delete(LogWork logWork) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOG_WORK)) {
            preparedStatement.setLong(1, logWork.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<LogWork> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_LOG_WORKS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<LogWork> getLogWorksByEstimation(Long estimationId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_LOG_WORKS)) {
             return parsDataByEstimation(preparedStatement.executeQuery(),estimationId);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<LogWork> parsData(ResultSet resultSet) {
        ArrayList<LogWork> logWorks = new ArrayList<>();
        try {
            while (resultSet.next()) {
                LogWork logWork = new LogWork(resultSet.getLong("id"),
                        resultSet.getString("description"), resultSet.getInt("logged"),
                        resultSet.getLong("author_id"), resultSet.getLong("estimation_id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime());
                logWorks.add(logWork);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return logWorks;
    }
    private List<LogWork> parsDataByEstimation(ResultSet resultSet,Long estimationId) {
        ArrayList<LogWork> logWorks = new ArrayList<>();
        try {
            while (resultSet.next()) {
                LogWork logWork = new LogWork(resultSet.getLong("id"),
                        resultSet.getString("description"), resultSet.getInt("logged"),
                        resultSet.getLong("author_id"), resultSet.getLong("estimation_id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime());
               if(estimationId.equals(logWork.getEstimationId())) logWorks.add(logWork);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return logWorks;
    }
}
