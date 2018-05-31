package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrelloInfoRepoImp implements TrelloInfoRepo {

    private static final Logger LOGGER = LogManager.getLogger(TrelloInfoRepoImp.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_TRELLOINFO =
            "INSERT INTO users_trello (username, access_key, access_token, user_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_TRELLOINFO =
            "UPDATE users_trello SET username = ?, access_key = ?, access_token = ?, WHERE user_id = ?";
    private static final String DELETE_TRELLOINFO = "DELETE FROM users_trello WHERE id = ?";
    private static final String READ_TRELLOINFO = "SELECT * FROM users_trello WHERE id = ?";
    private static final String READ_ALL_TRELLOINFOS = "SELECT * FROM users_trello";



    @Override
    public TrelloInfo create(TrelloInfo trelloInfo) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TRELLOINFO,
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, trelloInfo.getUserTrelloName());
            preparedStatement.setString(2, trelloInfo.getUserTrelloKey());
            preparedStatement.setString(3, trelloInfo.getUserTrelloToken());
            preparedStatement.setLong(4, trelloInfo.getUserId());

            if (preparedStatement.executeUpdate() == 0)
                LOGGER.error("Creating trelloInfo failed, no rows affected");

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating trelloInfo failed, no ID obtained.");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public TrelloInfo read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_TRELLOINFO)) {
            preparedStatement.setLong(1, id);

            List<TrelloInfo> trelloInfos = getData(preparedStatement.executeQuery());
            if (!trelloInfos.isEmpty())
                return trelloInfos.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public TrelloInfo update(TrelloInfo trelloInfo) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRELLOINFO)) {
            preparedStatement.setString(1, trelloInfo.getUserTrelloName());
            preparedStatement.setString(2, trelloInfo.getUserTrelloKey());
            preparedStatement.setString(3, trelloInfo.getUserTrelloToken());

            if (preparedStatement.executeUpdate() > 0)
                return read(trelloInfo.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRELLOINFO)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<TrelloInfo> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_TRELLOINFOS)) {
            LOGGER.warn("loggger don`t work in read all TrelloInfos");
            return getData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private List<TrelloInfo> getData(ResultSet resultSet) {
        List<TrelloInfo> trelloInfos = new ArrayList<>();
        try {
            while (resultSet.next()) {
                TrelloInfo trelloInfo = new TrelloInfo();
                trelloInfo.setId(resultSet.getLong("id"));
                trelloInfo.setUserId(resultSet.getLong("user_id"));
                trelloInfo.setUserTrelloName(resultSet.getString("username"));
                trelloInfo.setUserTrelloKey(resultSet.getString("access_key"));
                trelloInfo.setUserTrelloToken(resultSet.getString("access_token"));

                trelloInfos.add(trelloInfo);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return trelloInfos;
    }
}
