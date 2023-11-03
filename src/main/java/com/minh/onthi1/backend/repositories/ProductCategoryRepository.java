package com.minh.onthi1.backend.repositories;


import com.minh.onthi1.backend.models.Product;
import com.minh.onthi1.backend.models.ProductCategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductCategoryRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;
    public ProductCategoryRepository(){
        em= Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
        trans= em.getTransaction();
    }
    public ProductCategory findByID(Long id){
        try{
            trans.begin();
            ProductCategory products= (ProductCategory) em.createNativeQuery("SELECT * FROM product_category WHERE ID= "+id+"", ProductCategory.class).getSingleResult();
            trans.commit();
            return products;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}
