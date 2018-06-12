package com.softserve.mosquito.services.api;

import com.softserve.mosquito.dtos.TrelloInfoDto;

import java.util.List;

public interface TrelloInfoService {

    TrelloInfoDto createTrelloInfo(TrelloInfoDto trelloInfo);

    TrelloInfoDto getTrelloInfoById(Long id);

    TrelloInfoDto updateTrelloInfo(TrelloInfoDto trelloInfo);

    void removeTrelloInfo(Long id);

    List<TrelloInfoDto> getAllTrelloInfos();

    TrelloInfoDto getTrelloInfoByUserId(Long userId);
}
