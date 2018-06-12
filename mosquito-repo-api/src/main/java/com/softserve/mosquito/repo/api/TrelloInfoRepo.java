package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.TrelloInfo;

import java.util.List;

public interface TrelloInfoRepo extends GenericCRUD<TrelloInfo> {

    List<TrelloInfo> readAll();
}
