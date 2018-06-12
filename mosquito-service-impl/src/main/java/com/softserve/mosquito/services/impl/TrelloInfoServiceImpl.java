package com.softserve.mosquito.services.impl;

import com.softserve.mosquito.dtos.TrelloInfoDto;
import com.softserve.mosquito.repo.api.TrelloInfoRepo;
import com.softserve.mosquito.services.api.TrelloInfoService;
import com.softserve.mosquito.transformer.impl.TrelloInfoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrelloInfoServiceImpl implements TrelloInfoService {

    private TrelloInfoRepo trelloInfoRepo;

    @Autowired
    public TrelloInfoServiceImpl(TrelloInfoRepo trelloInfoRepo) {
        this.trelloInfoRepo = trelloInfoRepo;
    }

    @Override
    public TrelloInfoDto save(TrelloInfoDto trelloInfo) {
        return TrelloInfoTransformer.toDto(trelloInfoRepo.create(TrelloInfoTransformer.toEntity(trelloInfo)));
    }

    @Override
    public TrelloInfoDto getById(Long id) {

        return TrelloInfoTransformer.toDto(trelloInfoRepo.read(id));
    }

    @Override
    public TrelloInfoDto update(TrelloInfoDto trelloInfo) {
        return TrelloInfoTransformer.toDto(trelloInfoRepo.update(TrelloInfoTransformer.toEntity(trelloInfo)));
    }

    @Override
    public void delete(Long id) {
        trelloInfoRepo.delete(id);
    }

    @Override
    public List<TrelloInfoDto> getAll() {
        return TrelloInfoTransformer.toDto(trelloInfoRepo.getAll());
    }

    public Long getTrelloInfoIdByUserId(Long userId){
        for (TrelloInfoDto trelloInfo : getAll()){
            if (trelloInfo.getUser().getId()==userId) return trelloInfo.getId();
        }
        return null;
    }

    @Override
    @Transactional
    public TrelloInfoDto getByUserId(Long userId){
        return getById(getTrelloInfoIdByUserId(userId));
    }
}