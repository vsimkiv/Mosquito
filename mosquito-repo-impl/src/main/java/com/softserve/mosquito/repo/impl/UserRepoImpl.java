package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.User;
import com.softserve.mosquito.repo.api.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private static final Logger LOGGER = LogManager.getLogger(UserRepoImpl.class);
    private DataSource dataSource;

    public UserRepoImpl() {
        dataSource = MySqlDataSource.getDataSource();
    }

    public UserRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String CREATE_USER =
            "INSERT INTO users (email, password, first_name, last_name) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER =
            "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String READ_USER = "SELECT * FROM users WHERE id = ?";
    private static final String READ_ALL_USERS = "SELECT * FROM users";
    private static final String READ_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    @Override
    public User create(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());

            if (preparedStatement.executeUpdate() == 0)
                LOGGER.error("Creating user failed, no rows affected");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_USER)) {
            preparedStatement.setLong(1, id);

            List<User> users = getData(preparedStatement.executeQuery());
            if (!users.isEmpty())
                return users.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public User update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setLong(5, user.getId());

            if (preparedStatement.executeUpdate() > 0)
                return read(user.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_USERS)) {
            return getData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public User readUserByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);

            List<User> users = getData(preparedStatement.executeQuery());
            if (!users.isEmpty())
                return users.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private List<User> getData(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));

                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return users;
    }
}
