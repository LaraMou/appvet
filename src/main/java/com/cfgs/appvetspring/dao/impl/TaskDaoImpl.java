package com.cfgs.appvetspring.dao.impl;


import com.cfgs.appvetspring.dao.TaskDao;
import com.cfgs.appvetspring.model.Task;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskDaoImpl implements TaskDao {


    @PersistenceContext
    private EntityManager manager;




    @Override
    public List<Task> findAllTaskByFinish(Boolean finish) {
        if(finish!=null) {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> itemRoot = criteriaQuery.from(Task.class);
            Predicate predicateForFinish = criteriaBuilder.equal(itemRoot.get("finish"), finish);
            criteriaQuery.where(predicateForFinish);
            List<Task> items = manager.createQuery(criteriaQuery).getResultList();
            return items;
        }
        return null;
    }

    @Override
    public List<Task> findByEstado(String estado) {
        if(estado!=null) {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> itemRoot = criteriaQuery.from(Task.class);
            Predicate predicateEstado = criteriaBuilder.equal(itemRoot.get("estado"), estado);
            criteriaQuery.where(predicateEstado);
            List<Task> items = manager.createQuery(criteriaQuery).getResultList();
            return items;
        }
        return null;
    }


    @Override
    public List<Task> findAllTasks() {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> itemRoot = criteriaQuery.from(Task.class);
        criteriaQuery.select(itemRoot);

        List<Task> items = manager.createQuery(criteriaQuery).getResultList();
        manager.close();
        return items;
    }

    @Override
    public Optional<Task> findTaskByID(Long id) {
        if(id!=null) {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Task> criteria = builder.createQuery(Task.class);
            Root<Task> root = criteria.from(Task.class);
            criteria.select(root);
            criteria.where(builder.equal(root.get("id"), id));
            Task item = manager.createQuery(criteria).getSingleResult();
            manager.close();
            return Optional.of(item);
        }
        return Optional.empty();
    }
}
