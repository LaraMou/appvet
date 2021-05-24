package com.cfgs.appvetspring.dao.impl;

import com.cfgs.appvetspring.dao.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;
    @Override
    @Transactional
    public void insertUserTask(Long userId, Long taskId) {
        manager.createNativeQuery("INSERT INTO user_task (user_id,task_id) values (?,?)")
                .setParameter(1,userId)
                .setParameter(2,taskId)
                .executeUpdate();
        System.out.println("hola");
    }
}
