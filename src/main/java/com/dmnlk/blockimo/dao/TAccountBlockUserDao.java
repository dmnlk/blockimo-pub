package com.dmnlk.blockimo.dao;

import com.dmnlk.blockimo.entity.TAccountBlockUser;
import com.dmnlk.blockimo.util.doma.ComponentAndAutowiredDomaConfig;
import org.seasar.doma.*;

import java.util.List;

/**
 */
@Dao
@ComponentAndAutowiredDomaConfig
public interface TAccountBlockUserDao {

    /**
     * @param twitterId
     * @param blockUserTwitterId
     * @return the TAccountBlockUser entity
     */
    @Select
    TAccountBlockUser selectById(Long twitterId, Long blockUserTwitterId);

    /**
     * count by id
     * @param twitterId twitterid
     * @return sum
     */
    @Select
    int countById(Long twitterId);

    /**
     * get all data
     * @param twitterId twitterId
     * @return data
     */
    @Select
    List<TAccountBlockUser> selectUserAll(Long twitterId);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(TAccountBlockUser entity);

    /**
     *
     * @param entities
     * @return
     */
    @BatchInsert
    int[] inserts(List<TAccountBlockUser> entities);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(TAccountBlockUser entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TAccountBlockUser entity);

    /**
     * delete all data
     * @param twitterId twitterid
     * @return affected rows
     */
    @Delete(sqlFile = true)
    int deleteAccountRecord(Long twitterId);
}