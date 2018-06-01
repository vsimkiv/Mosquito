package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Specialization;
import com.softserve.mosquito.repo.api.SpecializationRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpecializationRepoImpl implements SpecializationRepo {
    private static final Logger LOGGER = LogManager.getLogger(SpecializationRepoImpl.class);
    private DataSource dataSource;

    public SpecializationRepoImpl() {
        dataSource = MySqlDataSource.getDataSource();
    }

    public SpecializationRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private static final String CREATE_SPECIALIZATION = "INSERT INTO specializations (title) VALUE (?);";
    private static final String UPDATE_SPECIALIZATION = "UPDATE specializations SET title=? WHERE id=?;";
    private static final String DELETE_SPECIALIZATION = "DELETE FROM specializations WHERE id=?;";
    private static final String READ_SPECIALIZATION = "SELECT * FROM specializations WHERE id=?;";
    private static final String READ_ALL_SPECIALIZATIONS = "SELECT * FROM specializations";

    @Override
    public Specialization create(Specialization specialization) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SPECIALIZATION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, specialization.getTitle());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0)
                LOGGER.error("Set up specialization was failed");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return read(generatedKeys.getLong(1));
                } else {
                    LOGGER.error("Set up specialization was failed");
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_SPECIALIZATION)) {
            preparedStatement.setLong(1, id);
            List<Specialization> specializations = parsData(preparedStatement.executeQuery());
            if (!specializations.isEmpty())
                return specializations.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Specialization update(Specialization specialization) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPECIALIZATION)) {
            preparedStatement.setString(1, specialization.getTitle());
            preparedStatement.setByte(2, specialization.getId());
            if (preparedStatement.executeUpdate() > 0)
                return specialization;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SPECIALIZATION)) {
            preparedStatement.setByte(1, Byte.parseByte(String.valueOf(id)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Specialization> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_SPECIALIZATIONS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return Collections.emptyList();
    }

    private List<Specialization> parsData(ResultSet resultSet) {
        List<Specialization> specializations = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Specialization specialization = new Specialization();
                specialization.setId(resultSet.getByte("id"));
                specialization.setTitle(resultSet.getString("title"));
                specializations.add(specialization);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return specializations;
    }
}
