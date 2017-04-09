package com.dmnlk.blockimo.service;


import com.dmnlk.blockimo.dao.TAccountDao;
import com.dmnlk.blockimo.entity.TAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountService {
    @Autowired
    private TAccountDao tAccountDao;

    public TAccount findTAccount(long twitterId) {
        TAccount tAccount = tAccountDao.selectById(twitterId);
        return  tAccount;
    }

    public List<TAccount> findAll() {
        return tAccountDao.selectAll();
    }

    public void tAccountInsert(TAccount tAccount) {
        tAccountDao.insert(tAccount);
    }

    public void tAccountUpdate(TAccount tAccount) {
        tAccountDao.update(tAccount);
    }
}
