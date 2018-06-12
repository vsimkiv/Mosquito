package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TrelloInfoDto;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import com.softserve.mosquito.services.api.TrelloInfoService;
import com.softserve.mosquito.transformer.impl.TrelloInfoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrelloInfoServiceImpl implements TrelloInfoService {

    private TrelloInfoRepo trelloInfoRepo;

    @Autowired
    public TrelloInfoServiceImpl(TrelloInfoRepo trelloInfoRepo) {
        this.trelloInfoRepo = trelloInfoRepo;
    }

    @Override
    public TrelloInfoDto createTrelloInfo(TrelloInfoDto trelloInfo) {
        return TrelloInfoTransformer.toDto(trelloInfoRepo.create(TrelloInfoTransformer.toEntity(trelloInfo)));
    }

    @Override
    public TrelloInfoDto getTrelloInfoById(Long id) {

        return TrelloInfoTransformer.toDto(trelloInfoRepo.read(id));
    }

    @Override
    public TrelloInfoDto updateTrelloInfo(TrelloInfoDto trelloInfo) {
        return TrelloInfoTransformer.toDto(trelloInfoRepo.update(TrelloInfoTransformer.toEntity(trelloInfo)));
    }

    @Override
    public void removeTrelloInfo(Long id) {
        trelloInfoRepo.delete(id);
    }

    @Override
    public List<TrelloInfoDto> getAllTrelloInfos() {
        return TrelloInfoTransformer.toDto(trelloInfoRepo.readAll());
    }

    public Long getTrelloInfoIdByUserId(Long userId){
        for (TrelloInfoDto trelloInfo : getAllTrelloInfos()){
            if (trelloInfo.getUser().getId()==userId) return trelloInfo.getId();
        }
        return null;
    }

    public TrelloInfoDto getTrelloInfoByUserId(Long userId){
        return getTrelloInfoById(getTrelloInfoIdByUserId(userId));
    }
}