package com.minh.onthi1.backend.repositories;

import com.minh.onthi1.backend.models.ProductImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductImageRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;
    public ProductImageRepository(){
        em= Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
        trans= em.getTransaction();
    }


    public boolean addProductImage(ProductImage productImage){
        try{
            trans.begin();
            em.persist(productImage);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error("Error: "+e.getMessage());
            return false;
        }
    }
    public ProductImage findByID(Long id){
        try{
            trans.begin();
            ProductImage image= (ProductImage) em.createNativeQuery("select * from product_image where IMAGE_ID = "+id+"", ProductImage.class).getSingleResult();
            trans.commit();
            return image;
        }catch (Exception e){
            logger.error("Error when find Image: "+e.getMessage());
            trans.rollback();
            return null;
        }
    }
    public boolean removeProductImage(long id){
        try{
            trans.begin();
            ProductImage productImage = em.find(ProductImage.class, id);
            productImage.setImage_id(id);
            em.remove(productImage);
            trans.commit();
            logger.warn("Xóa thành công");
            return true;
        }catch (Exception e){
            logger.error("Error when remove : "+e.getMessage());
            trans.rollback();
            return false;
        }
    }
    public List<ProductImage> getImageByIDProduct(Long id){
        try{
            trans.begin();
            List<ProductImage> images= em.createNativeQuery("select * from product_image where product_id = "+id+"", ProductImage.class).getResultList();
            trans.commit();
            return images ;
        }catch (Exception e){
            logger.error("Error: "+e.getMessage());
            trans.rollback();
            return null;
        }
    }
}
