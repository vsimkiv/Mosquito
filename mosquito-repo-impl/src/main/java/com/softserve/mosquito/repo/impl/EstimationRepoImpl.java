package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Estimation;
import com.softserve.mosquito.entities.LogWork;
import com.softserve.mosquito.repo.api.EstimationRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class EstimationRepoImpl implements EstimationRepo {

    private static final Logger LOGGER = LogManager.getLogger(EstimationRepoImpl.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_ESTIMATION =
            "INSERT INTO estimations (estimation,remaining) VALUE (?,?);";
    private static final String UPDATE_ESTIMATION =
            "UPDATE estimations SET estimation=?, remaining=? WHERE id=?;";
    private static final String DELETE_ESTIMATION =
            "DELETE FROM estimations WHERE id=?;";

    private static final String READ_ESTIMATION =
            "SELECT * FROM estimations e LEFT JOIN log_works l ON e.id=l.estimation_id  WHERE e.id=?;";
    private static final String READ_ALL_ESTIMATIONS = "SELECT * FROM estimations " +
            "LEFT JOIN log_works USING(id);";

    @Override
    public Estimation create(Estimation estimation) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ESTIMATION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, estimation.getTimeEstimation());
            if(estimation.getRemaining()!= null)
                preparedStatement.setInt(2, estimation.getRemaining());
            else
                preparedStatement.setInt(2, 0);
            preparedStatement.execute();

            if (preparedStatement.executeUpdate() == 0)
                LOGGER.error("Creating estimation was failed");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating estimation was failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public Estimation read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ESTIMATION)) {
            preparedStatement.setLong(1, id);
            List<Estimation> estimations = parsData(preparedStatement.executeQuery());
            if (!estimations.isEmpty())
                return estimations.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Estimation update(Estimation estimation) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ESTIMATION)) {
            preparedStatement.setLong(3, estimation.getId());
            preparedStatement.setInt(1, estimation.getTimeEstimation());
            preparedStatement.setInt(2, estimation.getRemaining());

            if (preparedStatement.executeUpdate() > 0)
                return read(estimation.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return estimation;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ESTIMATION)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Estimation> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_ESTIMATIONS)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<Estimation> parsData(ResultSet resultSet) {
        HashSet<Estimation> estimations = new HashSet<>();
        try {
            while (resultSet.next()) {
                Estimation estimation = new Estimation(
                        resultSet.getLong("e.id"),
                        resultSet.getInt("estimation"),
                        resultSet.getInt("remaining"));

                if (resultSet.getLong("l.id") != 0) {
                    LogWork logWork = new LogWork(
                           /* resultSet.getLong("l.id"),
                            resultSet.getString("description"),
                            resultSet.getInt("logged"),
                            resultSet.getLong("user_id"),
                            resultSet.getLong("estimation_id"),
                            resultSet.getTimestamp("last_update").toLocalDateTime()*/);
                    estimation.getLogWorks().add(logWork);

                    for (Estimation item : estimations)
                        if (item.getId().equals(estimation.getId())) {
                            item.getLogWorks().add(logWork);
                            break;
                        }
                } else
                    estimations.add(estimation);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>(estimations);
    }
}
