package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
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
    public TrelloInfo create(TrelloInfo entity) {
        return null;
    }

    @Override
    public TrelloInfo read(Long id) {
        return null;
    }

    @Override
    public TrelloInfo update(TrelloInfo entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<TrelloInfo> readAll() {
        return null;
    }
}
