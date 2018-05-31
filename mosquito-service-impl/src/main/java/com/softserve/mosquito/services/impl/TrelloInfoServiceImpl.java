package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.entities.TrelloInfo;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import com.softserve.mosquito.repo.impl.TrelloInfoRepoImp;
import com.softserve.mosquito.services.api.TrelloInfoService;

import java.util.List;

public class TrelloInfoServiceImpl implements TrelloInfoService {

    private TrelloInfoRepo trelloInfoRepo = new TrelloInfoRepoImp();

    @Override
    public TrelloInfo createTrelloInfo(TrelloInfo trelloInfo) {
        return trelloInfoRepo.create(trelloInfo);
    }

    @Override
    public TrelloInfo getTrelloInfoById(Long id) {
        return trelloInfoRepo.read(id);
    }

    @Override
    public TrelloInfo updateTrelloInfo(TrelloInfo trelloInfo) {
        return trelloInfoRepo.update(trelloInfo);
    }

    @Override
    public void removeTrelloInfo(Long id) {
        trelloInfoRepo.delete(id);
    }

    @Override
    public List<TrelloInfo> getAllTrelloInfos() {
        return trelloInfoRepo.readAll();
    }
}
