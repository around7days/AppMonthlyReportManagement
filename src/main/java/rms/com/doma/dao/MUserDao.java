package rms.com.doma.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import rms.com.doma.entity.MUser;

/**
 * MUserDaoクラス
 */
@Dao
@ConfigAutowireable
public interface MUserDao {

    /**
     * selectById
     * @param userId
     * @return the MUser entity
     */
    @Select
    MUser selectById(String userId);

    /**
     * insert
     * @param entity
     * @return affected rows
     */
    @Insert(excludeNull = true)
    int insert(MUser entity);

    /**
     * udpate
     * @param entity
     * @return affected rows
     */
    @Update(excludeNull = true)
    int update(MUser entity);

    /**
     * delete
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(MUser entity);
}