//package com.softserve.mosquito.services.impl;
//
//import com.softserve.mosquito.entities.TrelloInfo;
//import com.softserve.mosquito.repo.impl.TrelloInfoRepoImp;
//import com.softserve.mosquito.services.api.TrelloInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.List;
//
//@Service
//public class TrelloInfoServiceImpl implements TrelloInfoService {
//
//    private TrelloInfoRepoImp trelloInfoRepoImp;
//
//    @Autowired
//    public TrelloInfoServiceImpl(TrelloInfoRepoImp trelloInfoRepoImp) {
//        this.trelloInfoRepoImp = trelloInfoRepoImp;
//    }
//
//    @Override
//    @Transactional
//    public TrelloInfo createTrelloInfo(TrelloInfo trelloInfo) {
//        return trelloInfoRepoImp.create(trelloInfo);
//    }
//
//    @Override
//    @Transactional
//    public TrelloInfo getTrelloInfoById(Long id) {
//        return trelloInfoRepoImp.read(id);
//    }
//
//    @Override
//    @Transactional
//    public TrelloInfo updateTrelloInfo(TrelloInfo trelloInfo) {
//        return trelloInfoRepoImp.update(trelloInfo);
//    }
//
//    @Override
//    @Transactional
//    public void removeTrelloInfo(Long id) {
//        trelloInfoRepoImp.delete(id);
//    }
//
//    @Override
//    @Transactional
//    public List<TrelloInfo> getAllTrelloInfos() {
//        return trelloInfoRepoImp.readAll();
//    }
//
//    public Long getTrelloInfoIdByUserId(Long userId){
//        for (TrelloInfo trelloInfo : getAllTrelloInfos()){
//            if (trelloInfo.getUserId()==userId) return trelloInfo.getId();
//        }
//        return null;
//    }
//
//    @Transactional
//    public TrelloInfo getTrelloInfoByUserId(Long userId){
//        return getTrelloInfoById(getTrelloInfoIdByUserId(userId));
//    }
//}
