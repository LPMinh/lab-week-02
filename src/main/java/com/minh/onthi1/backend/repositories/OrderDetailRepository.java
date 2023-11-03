package com.minh.onthi1.backend.repositories;


import com.minh.onthi1.backend.models.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderDetailRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;

    public OrderDetailRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public Boolean insertOrderDetail(OrderDetail orderDetail) {
        try {
            trans.begin();
            em.persist(orderDetail);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
            return false;
        }
    }


}
