package com.softserve.mosquito.transformer.impl;

import com.softserve.mosquito.dtos.TrelloInfoDto;
import com.softserve.mosquito.entities.TrelloInfo;

import java.util.ArrayList;
import java.util.List;

public class TrelloInfoTransformer {

    private TrelloInfoTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static TrelloInfo toEntity(TrelloInfoDto trelloInfoDto){
        return new TrelloInfo(trelloInfoDto.getId(), trelloInfoDto.getUser(), trelloInfoDto.getUserTrelloName(),
                trelloInfoDto.getUserTrelloKey(), trelloInfoDto.getUserTrelloToken());
    }

    public static TrelloInfoDto toDto(TrelloInfo trelloInfo){
        return new TrelloInfoDto(trelloInfo.getId(), trelloInfo.getUser(), trelloInfo.getUserTrelloName(),
                trelloInfo.getUserTrelloKey(), trelloInfo.getUserTrelloToken());
    }

    public static List<TrelloInfoDto>  toDto(List<TrelloInfo> trelloInfos){
        if (trelloInfos == null) return null;
        else {
            List<TrelloInfoDto> trelloInfoDtos = new  ArrayList<TrelloInfoDto>();
            for (TrelloInfo info : trelloInfos){
                trelloInfoDtos.add(toDto(info));
            }
            return trelloInfoDtos;
        }
    }

}
