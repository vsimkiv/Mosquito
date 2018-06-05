package com.softserve.mosquito.repo.impl;


import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.repo.api.CommentRepo;
import com.sun.xml.internal.stream.events.NotationDeclarationImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentRepoImpl implements CommentRepo {

    private static final Logger LOGGER = LogManager.getLogger(CommentRepoImpl.class);
    private DataSource dataSource = MySqlDataSource.getDataSource();

    private static final String CREATE_COMMENT =
            "INSERT INTO comments (text, task_id, author_id) VALUES(?,?,?);";
    private static final String UPDATE_COMMENT =
            "UPDATE comments SET text=? WHERE id=?;";
    private static final String DELETE_COMMENT =
            "DELETE FROM comments WHERE id=?";
    private static final String READ_COMMENT = "SELECT * FROM comments WHERE task_id=?;";
    private static final String READ_ALL_COMMENTS = "SELECT * FROM comments;";

    @Override
    public Comment create(Comment comment) {
        throw new NotImplementedException();
        /*try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COMMENT)) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setLong(2, comment.getTaskId());
            preparedStatement.setLong(3, comment.getAuthorId());

            if (preparedStatement.executeUpdate() == 0)
                LOGGER.error("Creating comment was failed");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    return read(generatedKeys.getLong(1));
                else
                    LOGGER.error("Creating comment was failed");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;*/
    }

    @Override
    public Comment read(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_COMMENT)) {
            preparedStatement.setLong(1, id);
            List<Comment> comments = parseData(preparedStatement.executeQuery());
            if (!comments.isEmpty())
                return comments.iterator().next();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Comment update(Comment comment) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            preparedStatement.setString(1, comment.getText());
            preparedStatement.setLong(2, comment.getId());
            if (preparedStatement.executeUpdate() > 0)
                return read(comment.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return comment;
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public List<Comment> readAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_COMMENTS)) {
            return parseData(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<Comment> parseData(ResultSet resultSet) {
        throw new NotImplementedException();
        /*List<Comment> comments = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Comment comment = new Comment(resultSet.getLong("id"),
                        resultSet.getString("text"),
                        resultSet.getLong("task_id"),
                        resultSet.getLong("author_id"),
                        resultSet.getTimestamp("last_update").toLocalDateTime());
                comments.add(comment);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return comments;*/
    }
}
