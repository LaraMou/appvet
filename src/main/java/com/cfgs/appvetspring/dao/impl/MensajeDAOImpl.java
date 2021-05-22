package com.cfgs.appvetspring.dao.impl;

import com.cfgs.appvetspring.dao.MensajeDAO;
import com.cfgs.appvetspring.model.Mensaje;
import com.cfgs.appvetspring.model.Mensaje;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class MensajeDAOImpl  implements MensajeDAO {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Mensaje> findByUserId(Long id) {
            if(id!=null) {
                CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
                CriteriaQuery<Mensaje> criteriaQuery = criteriaBuilder.createQuery(Mensaje.class);
                Root<Mensaje> itemRoot = criteriaQuery.from(Mensaje.class);
                Predicate predicate = criteriaBuilder.equal(itemRoot.get("user"), id);
                criteriaQuery.where(predicate);
                List<Mensaje> items = manager.createQuery(criteriaQuery).getResultList();
                return items;
            }
            return null;
        }


    @Override
    public List<Mensaje> findByTaskId(Long id) {

        if(id!=null) {
            CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
            CriteriaQuery<Mensaje> criteriaQuery = criteriaBuilder.createQuery(Mensaje.class);
            Root<Mensaje> itemRoot = criteriaQuery.from(Mensaje.class);
            Predicate predicate = criteriaBuilder.equal(itemRoot.get("task"), id);
            criteriaQuery.where(predicate);
            List<Mensaje> items = manager.createQuery(criteriaQuery).getResultList();
            return items;
        }
        return null;
    }
}
