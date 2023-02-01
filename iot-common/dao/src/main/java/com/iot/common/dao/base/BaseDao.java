package com.iot.common.dao.base;

import com.iot.common.entity.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Long> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {

    /**
     * soft delete
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    @Query("update #{#entityName} e set e.deleted=NULL where e.id=:id")
    @Modifying
    void deleteSoftById(@Param("id") Long id);


    @Transactional(rollbackFor = Exception.class)
    @Query("update #{#entityName} e set e.deleted=NULL where e.id in (:ids)")
    @Modifying
    void deleteAllSoftById(@Param("ids") Iterable<Long> ids);

    @Transactional(rollbackFor = Exception.class)
    default void deleteSoft(T t) {
        deleteSoftById(t.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    default void deleteAllSoft(Iterable<T> ts) {
        ts.forEach(it -> deleteSoftById(it.getId()));
    }
}
