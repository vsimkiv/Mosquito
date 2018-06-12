package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TrelloInfoDto;

import java.util.List;

public interface TrelloInfoService {

    TrelloInfoDto save(TrelloInfoDto trelloInfo);

    TrelloInfoDto getById(Long id);

    TrelloInfoDto update(TrelloInfoDto trelloInfo);

    void delete(Long id);

    List<TrelloInfoDto> getAll();

    TrelloInfoDto getByUserId(Long userId);
}
