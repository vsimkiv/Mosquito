package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.entities.Status;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.repo.api.TaskRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskRepoImpl implements TaskRepo {
    private static final Logger LOGGER = LogManager.getLogger(TaskRepoImpl.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_TASK =
            "INSERT INTO tasks (name, parent_id, owner_id, worker_id, " +
            "estimation_id, priority_id, status_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TASK =
            "UPDATE tasks SET name = ?, worker_id = ?, " +
            "priority_id =?, status_id = ? WHERE id = ?";
    private static final String DELETE_TASK = "DELETE FROM tasks WHERE id = ?";
    private static final String READ_TASK =
            "SELECT * FROM tasks t JOIN statuses s ON t.status_id=s.id " +
            "JOIN estimations e ON t.estimation_id=e.id " +
            "JOIN priorities p ON t.priority_id=p.id WHERE t.id=?;";
    private static final String READ_ALL_TASKS =
            "SELECT * FROM tasks t JOIN statuses s ON t.status_id=s.id " +
            "JOIN estimations e ON t.estimation_id=e.id JOIN priorities p ON t.priority_id=p.id;";

    @Override
    public Task create(Task task) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TASK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, task.getName());
            if(task.getParentId() != null)
                preparedStatement.setLong(2, task.getParentId());
            else
                preparedStatement.setNull(2, Types.BIGINT);
            preparedStatement.setLong(3, task.getOwnerId());
            preparedStatement.setLong(4, task.getWorkerId());
            preparedStatement.setLong(5, task.getEstimation().getId());
            preparedStatement.setByte(6, task.getPriority().getId());
            preparedStatement.setByte(7, task.getStatus().getId());

            if (preparedStatement.executeUpdate() == 0)
                LOGGER.error("Creating task failed");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating task failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL error:" + e.getMessage(), e);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Task read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_TASK)) {
            preparedStatement.setLong(1, id);
            List<Task> tasks = getData(preparedStatement.executeQuery());
            if (!tasks.isEmpty())
                return tasks.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Task update(Task task) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TASK)) {
            preparedStatement.setString(1, task.getName());
            preparedStatement.setLong(2, task.getWorkerId());
            preparedStatement.setByte(3, task.getPriority().getId());
            preparedStatement.setByte(4, task.getStatus().getId());
            preparedStatement.setLong(5, task.getId());

            if (preparedStatement.executeUpdate() > 0)
                return read(task.getId());

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TASK)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Task> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_TASKS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return getData(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private List<Task> getData(ResultSet resultSet) {
        List<Task> tasks = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setParentId(resultSet.getLong("parent_id"));
                task.setOwnerId(resultSet.getLong("owner_id"));
                task.setWorkerId(resultSet.getLong("worker_id"));
                task.setEstimation(new Estimation(
                        resultSet.getLong(4),
                        resultSet.getInt(12),
                        resultSet.getInt(13)));
                task.setPriority(new Priority(
                        resultSet.getByte(7),
                        resultSet.getString(15)));
                task.setStatus(new Status(
                        resultSet.getByte(8),
                        resultSet.getString(10)));
                tasks.add(task);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return tasks;
    }
}
