package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TrelloInfoDto;
import com.softserve.mosquito.repo.impl.TrelloInfoRepoImp;
import com.softserve.mosquito.services.api.TrelloInfoService;
import com.softserve.mosquito.transformer.impl.TrelloInfoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class TrelloInfoServiceImpl implements TrelloInfoService {

    private TrelloInfoRepoImp trelloInfoRepoImp;

    @Autowired
    public TrelloInfoServiceImpl(TrelloInfoRepoImp trelloInfoRepoImp) {
        this.trelloInfoRepoImp = trelloInfoRepoImp;
    }

    @Override
    @Transactional
    public TrelloInfoDto createTrelloInfo(TrelloInfoDto trelloInfo) {
        return TrelloInfoTransformer.toDto(trelloInfoRepoImp.create(TrelloInfoTransformer.toEntity(trelloInfo)));
    }

    @Override
    @Transactional
    public TrelloInfoDto getTrelloInfoById(Long id) {

        return TrelloInfoTransformer.toDto(trelloInfoRepoImp.read(id));
    }

    @Override
    @Transactional
    public TrelloInfoDto updateTrelloInfo(TrelloInfoDto trelloInfo) {
        return TrelloInfoTransformer.toDto(trelloInfoRepoImp.update(TrelloInfoTransformer.toEntity(trelloInfo)));
    }

    @Override
    @Transactional
    public void removeTrelloInfo(Long id) {
        trelloInfoRepoImp.delete(id);
    }

    @Override
    @Transactional
    public List<TrelloInfoDto> getAllTrelloInfos() {
        return TrelloInfoTransformer.toDto(trelloInfoRepoImp.readAll());
    }

    public Long getTrelloInfoIdByUserId(Long userId){
        for (TrelloInfoDto trelloInfo : getAllTrelloInfos()){
            if (trelloInfo.getUserId()==userId) return trelloInfo.getId();
        }
        return null;
    }

    @Transactional
    public TrelloInfoDto getTrelloInfoByUserId(Long userId){
        return getTrelloInfoById(getTrelloInfoIdByUserId(userId));
    }
}