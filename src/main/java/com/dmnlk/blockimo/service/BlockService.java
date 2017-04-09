package com.dmnlk.blockimo.service;

import com.dmnlk.blockimo.dao.TAccountBlockUserDao;
import com.dmnlk.blockimo.dao.TBlockQueueDao;
import com.dmnlk.blockimo.dao.TUserQueueDao;
import com.dmnlk.blockimo.entity.TAccountBlockUser;
import com.dmnlk.blockimo.entity.TBlockQueue;
import com.dmnlk.blockimo.entity.TUserQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlockService {
    @Autowired
    private TBlockQueueDao tBlockQueueDao;
    @Autowired
    private TUserQueueDao tUserQueueDao;
    @Autowired
    private TAccountBlockUserDao tAccountBlockUserDao;


    public void registerBlockUser(List<TBlockQueue> blockQueueList) {
        tBlockQueueDao.inserts(blockQueueList);
    }

    public List<TBlockQueue> findAllAccountBlockQueue(Long twitterId) {
        return tBlockQueueDao.selectYetProcessQueueByTwitterId(twitterId);
    }

    public TBlockQueue findByTwitterIdAndBlockTwitterId(Long twitterId, Long blockTwitterId) {
        return tBlockQueueDao.selectByTwitterIdAndBlockId(twitterId, blockTwitterId);
    }

    public void updateTBlockQueueList(List<TBlockQueue> list) {
        tBlockQueueDao.updates(list);
    }

    public void registerUser(TUserQueue tUserQueue) {
        tUserQueueDao.insert(tUserQueue);
    }

    public List<TUserQueue> findAllUserQueue(Long twitterId) {
        return tUserQueueDao.selectByTwitterId(twitterId);
    };

    public void updateTUserQueue(TUserQueue tUserQueue) {
        tUserQueueDao.update(tUserQueue);
    }

    public void deleteUserQueue(TUserQueue tUserQueue) {
        tUserQueueDao.delete(tUserQueue);
    }

    public TAccountBlockUser findTAccountBlockUserById(Long twitterId, Long blockTwitterId) {
        return tAccountBlockUserDao.selectById(twitterId, blockTwitterId);
    }

    public void insertTTAccountBlockUser(TAccountBlockUser entity) {
        tAccountBlockUserDao.insert(entity);
    }

    public void insertsTAccountBlockUser(List<TAccountBlockUser> entities) {
        tAccountBlockUserDao.inserts(entities);
    }

    public int countTodayBlock(Long twitterId) {
        return tBlockQueueDao.countTodayBlock(twitterId);
    }

    public List<TAccountBlockUser> getAllUserData(Long twitterId) {
        return  tAccountBlockUserDao.selectUserAll(twitterId);
    }
}
