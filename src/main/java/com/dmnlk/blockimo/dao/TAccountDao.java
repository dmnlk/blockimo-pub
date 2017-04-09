package com.dmnlk.blockimo.dao;

import com.dmnlk.blockimo.entity.TAccount;
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
public interface TAccountDao {

    /**
     * @param twitterId
     * @return the TAccount entity
     */
    @Select
    TAccount selectById(Long twitterId);

    @Select
    List<TAccount> selectAll();

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(TAccount entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(TAccount entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(TAccount entity);
}