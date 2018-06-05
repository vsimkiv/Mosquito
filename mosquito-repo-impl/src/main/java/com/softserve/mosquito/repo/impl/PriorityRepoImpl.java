package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Priority;
import com.softserve.mosquito.repo.api.PriorityRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class PriorityRepoImpl implements PriorityRepo {
    private static final Logger LOGGER = LogManager.getLogger(PriorityRepoImpl.class);

    private SessionFactory sessionFactory;

    @Autowired
    public PriorityRepoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    /*private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_PRIORITY = "INSERT INTO priorities (title) VALUE (?);";
    private static final String UPDATE_PRIORITY = "UPDATE priorities SET title=? WHERE id=?;";
    private static final String DELETE_PRIORITY = "DELETE FROM priorities WHERE id=?;";
    private static final String READ_PRIORITY = "SELECT * FROM priorities WHERE id=?;";
    private static final String READ_ALL_PRIORITIES = "SELECT * FROM priorities";*/

    @Override
    public Priority create(Priority priority) {
        throw new NotImplementedException();
        /*try (Connection connection = dataSource.getConnection();
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
        return null;*/
    }

    @Override
    public Priority read(Long id) {
        throw new NotImplementedException();
        /*try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_PRIORITY)) {
            preparedStatement.setLong(1, id);
            List<Priority> priorities = parsData(preparedStatement.executeQuery());
            if (!priorities.isEmpty())
                return priorities.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;*/
    }

    @Override
    public Priority update(Priority priority) {
        throw new NotImplementedException();

        /*try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRIORITY)) {
            preparedStatement.setString(1, priority.getTitle());
            preparedStatement.setByte(2, priority.getId());
            if (preparedStatement.executeUpdate() > 0)
                return priority;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return priority;*/
    }

    @Override
    public void delete(Long id) {
        throw new NotImplementedException();

        /*try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRIORITY)) {
            preparedStatement.setByte(1, Byte.parseByte(String.valueOf(id)));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }*/
    }

    @Override
    public List<Priority> readAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("From " + Priority.class.getName());
        return query.list();
        /*try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_PRIORITIES)) {
            return parsData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }*/
    }

    private List<Priority> parsData(ResultSet resultSet) {
        throw new NotImplementedException();
        /*List<Priority> priorities = new ArrayList<>();

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
        return priorities;*/
    }
}
