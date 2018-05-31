package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityRepoImpl implements PriorityRepo {
    private static final Logger LOGGER = LogManager.getLogger(PriorityRepoImpl.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_PRIORITY = "INSERT INTO priorities (title) VALUE (?);";
    private static final String UPDATE_PRIORITY = "UPDATE priorities SET title=? WHERE id=?;";
    private static final String DELETE_PRIORITY = "DELETE FROM priorities WHERE id=?;";
    private static final String READ_PRIORITY = "SELECT * FROM priorities WHERE id=?;";
    private static final String READ_ALL_PRIORITIES = "SELECT * FROM priorities";

    @Override
    public Priority create(Priority priority) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRIORITY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, priority.getTitle());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0)
                LOGGER.error("Set up priority was failed");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Set up was failed");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Priority read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_PRIORITY)) {
            preparedStatement.setLong(1, id);
            List<Priority> priorities = parsData(preparedStatement.executeQuery());
            if (!priorities.isEmpty())
                return priorities.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Priority update(Priority priority) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRIORITY)) {
            preparedStatement.setString(1, priority.getTitle());
            preparedStatement.setByte(2, priority.getId());
            if (preparedStatement.executeUpdate() > 0)
                return priority;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return priority;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRIORITY)) {
            preparedStatement.setByte(1, Byte.parseByte(String.valueOf(id)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Priority> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_PRIORITIES)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<Priority> parsData(ResultSet resultSet) {
        List<Priority> priorities = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Priority priority = new Priority();
                priority.setId(resultSet.getByte("id"));
                priority.setTitle(resultSet.getString("title"));
                priorities.add(priority);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return priorities;
    }
}
