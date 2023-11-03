package com.minh.onthi1.backend.repositories;


import com.minh.onthi1.backend.models.Product;
import com.minh.onthi1.backend.models.ProductPrice;
import com.minh.onthi1.backend.models.ProductStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public class ProductRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;

    public ProductRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public Boolean insertProduct(Product product) {
        try {
            trans.begin();
            em.persist(product);
            trans.commit();
            return true;
        } catch (Exception ex) {

            logger.error(ex.getMessage());
            trans.rollback();
            return false;
        }
    }

    public boolean setStatus(Product product, ProductStatus status) {
        product.setStatus(status);
        try {
            trans.begin();
            em.merge(product);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
            return false;
        }
    }

    public boolean removeProduct(long productID) {
        Product product = findProductById(productID).get();
        if (product == null) {
            return false;
        } else {
            return setStatus(product, ProductStatus.DISCONTINUED);
        }
    }

    public boolean update(Product product) {
        try {
            trans.begin();
            em.merge(product);
            trans.commit();
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            trans.rollback();

            return false;
        }
    }

    public Optional<Product> findProductById(long id) {
        try {
            trans.begin();
            String sql = "SELECT * FROM products WHERE productId = ?";
            Product product = (Product) em.createNativeQuery(sql, Product.class)
                    .setParameter(1, id)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            trans.commit();
            return product == null ? Optional.empty() : Optional.of(product);
        } catch (Exception ex) {
            trans.rollback();
            logger.error("Error while finding product by ID: " + ex.getMessage());
            return Optional.empty();
        }
    }

    public List<Product> getAllProduct() {
        try {
            trans.begin();
            List<Product> products = em.createNativeQuery("select * from products", Product.class).getResultList();

            trans.commit();
            logger.info(products.get(0).toString());
            return products;
        } catch (Exception ex) {
            trans.rollback();
            logger.error("Error while finding product by ID: " + ex.getMessage());
            return null;
        }
    }

    public double getPriceByID(Long idProduct) {
        try {
            trans.begin();
            TypedQuery<Double> query = em.createNamedQuery("ProductPrice.findByProductID", Double.class)
                    .setParameter("productID", idProduct)
                    .setFirstResult(0)  // First result index is 0
                    .setMaxResults(1);  // You want to get only the first result

            Double firstPrice = query.getSingleResult();
            trans.commit();
            return firstPrice;
        } catch (Exception e) {

            logger.error(e.getMessage());
            trans.rollback();
        }
        return 0;
    }

    public Product getProductByID(Long idProduct) {
        try {
            trans.begin();
            TypedQuery<Product> products = em.createNamedQuery("Product.selectByID", Product.class)
                    .setParameter("productID", idProduct)
                    .setFirstResult(0)
                    .setMaxResults(1);
            trans.commit();
            return products.getSingleResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            trans.rollback();
            return null;
        }

    }

    public boolean addProductPrice(ProductPrice productPrice) {
        try {
            trans.begin();
            em.merge(productPrice);

            trans.commit();

            return true;
        } catch (Exception ex) {
            trans.rollback();
            logger.error("Error while finding product by ID: " + ex.getMessage());
            return false;
        }
    }

    public List<Product> getAllProductByStatus(ProductStatus productStatus) {
        try {
            trans.begin();
            List<Product> pros = em.createNativeQuery("select * from products where  status=" + productStatus.getCode() + "", Product.class).getResultList();
            trans.commit();
            return pros;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Product> filterProduct(int productStatus, String sort) {
        try{
            trans.begin();
            List<Product> products=em.createNativeQuery("select * from products where status="+productStatus+" order by productId "+ sort +"", Product.class).getResultList();
            trans.commit();
            return products;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Product> searchProduct(String query) {
        try{
            trans.begin();
            List<Product> products=em.createNativeQuery("SELECT * FROM products WHERE name LIKE CONCAT('%', ?1, '%')", Product.class).setParameter(1,query).getResultList();
            trans.commit();
            return products;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }


}

