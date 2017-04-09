package com.dmnlk.blockimo.dao;

import com.dmnlk.blockimo.entity.TUserQueue;
import com.dmnlk.blockimo.util.doma.ComponentAndAutowiredDomaConfig;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import java.util.List;

/**
 */
@Dao
@ComponentAndAutowiredDomaConfig
public interface TUserQueueDao {

    /**
     * @param blockUserQueueSerialId
     * @return the TUserQueue entity
     */
    @Select
    TUserQueue selectById(Long blockUserQueueSerialId);

    @Select
    List<TUserQueue> selectByTwitterId(Long twitterId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(TUserQueue entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(TUserQueue entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TUserQueue entity);

    @Delete(sqlFile = true)
    int deleteAccountRecord(Long twitterId);
}