package com.dmnlk.blockimo.dao;

import com.dmnlk.blockimo.entity.TBlockQueue;
import com.dmnlk.blockimo.util.doma.ComponentAndAutowiredDomaConfig;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.SelectOptions;

import java.util.List;

/**
 */
@Dao
@ComponentAndAutowiredDomaConfig
public interface TBlockQueueDao {

    /**
     * @param blockQueueSerialId
     * @return the TBlockQueue entity
     */
    @Select
    TBlockQueue selectById(Long blockQueueSerialId);

    @Select
    List<TBlockQueue> selectYetProcessQueueByTwitterId(Long twitterId);
    @Select
    int countYetProcessQueueByTwitterId(Long twitterId);
    @Select
    TBlockQueue selectByTwitterIdAndBlockId(Long twitterId, Long blockTwitterId);
    @Select
    List<TBlockQueue> selectRecentBlockById(Long twitterId, SelectOptions options);

    @Select
    int countTodayBlock(Long twitterId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(TBlockQueue entity);

    @BatchInsert
    int[] inserts(List<TBlockQueue> entities);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(TBlockQueue entity);

    @BatchUpdate
    int[] updates(List<TBlockQueue> entities);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TBlockQueue entity);

    @Delete(sqlFile = true)
    int deleteAccountRecord(Long twitterId);
}