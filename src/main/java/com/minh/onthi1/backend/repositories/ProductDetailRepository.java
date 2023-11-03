package com.minh.onthi1.backend.repositories;

import com.minh.onthi1.backend.enums.Color;
import com.minh.onthi1.backend.enums.Size;
import com.minh.onthi1.backend.models.ProductCategory;
import com.minh.onthi1.backend.models.ProductDetail;
import com.minh.onthi1.backend.models.ProductImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;
    public ProductDetailRepository(){
        em= Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
        trans= em.getTransaction();
    }
    public ProductDetail findByID(Long id){
        try{
            trans.begin();
            ProductDetail products= (ProductDetail) em.createNativeQuery("SELECT * FROM product_category WHERE ID= "+id+"", ProductDetail.class).getSingleResult();
            trans.commit();
            return products;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    public boolean inserProductDetail(ProductDetail detail){
        try{
            trans.begin();
            em.persist(detail);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    public boolean removeProductDetail(ProductDetail detail){
        try{
            trans.begin();
            em.remove(detail);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    public boolean updateProductDetail(ProductDetail detail){
        try{
            trans.begin();
            em.merge(detail);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    public List<ProductDetail> getProductDetailbyIDProduct(Long id){
        try{
            trans.begin();
            List<ProductDetail> images= em.createNativeQuery("select * from product_detail where product_id = "+id+"", ProductDetail.class).getResultList();
            trans.commit();
            logger.warn("Lấy thành công: "+images.size());
            return images ;

        }catch (Exception e){
            logger.error("Error: "+e.getMessage());
            trans.rollback();
            return null;
        }
    }
    public List<Color> getColorByIDProduct(Long id) {
        try {
            trans.begin();
            List<Color> colors=em.createQuery("select distinct p.color from ProductDetail p where p.product.productId = "+id+"", Color.class).getResultList();

            trans.commit();
          return colors;
        }catch (Exception e){

            logger.error(e.getMessage());
            trans.rollback();
            return null;
        }
    }

    public List<Size> getSizeByColor(int idColor,Long idProduct) {
        try {
            trans.begin();
            Color color=Color.getColorFromInt(idColor);
            List<Object> colors=em.createNativeQuery("select distinct p.size from product_detail p where p.product_id = "+idProduct+" and p.color = "+color.ordinal()+"").getResultList();
            List<Size> sizes=new ArrayList<>();
            for (Object o:colors)
                  {
               int size= (int) o;
                sizes.add(Size.getSizeFromInt(size));
            }

            trans.commit();
            logger.warn("Lấy thành công: "+sizes.size());
            return sizes;
        }catch (Exception e){

            logger.error(e.getMessage());
            trans.rollback();
            return null;
        }
    }
}
