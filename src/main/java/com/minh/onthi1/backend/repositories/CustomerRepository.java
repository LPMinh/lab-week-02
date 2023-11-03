package com.minh.onthi1.backend.repositories;


import com.minh.onthi1.backend.models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public class CustomerRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;
    public CustomerRepository(){
        em=Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
        trans= em.getTransaction();
    }
    public boolean addCustomer(Customer cus){
        try{
            trans.begin();
            em.persist(cus);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    public boolean deleteCustomerByID(long id){
        try{
            trans.begin();
            Customer  cus=new Customer();
            cus.setCustId(id);
            em.remove(cus);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
    public Customer updateCustomer(Customer cus){
        try{
            trans.begin();
                Customer cust=em.merge(cus);
            trans.commit();
            return cust;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    public  List<Customer> findCustomerByNameOrID(String query){
        try {
            trans.begin();
            List<Customer> cust =(List<Customer>) em.createNativeQuery("SELECT * FROM customers WHERE custId LIKE CONCAT('%',?1, '%') OR email LIKE CONCAT('%',?2, '%') OR phone LIKE CONCAT ('%',?3, '%')", Customer.class)
                    .setParameter(1, query).setParameter(2,query).setParameter(3,query)
                    .getResultList();
            trans.commit();
            return cust;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
    public List<Customer> getAllCustomer(){
        try{
            trans.begin();
            List<Customer> cust= (List<Customer>) em.createNativeQuery("select * from customers",Customer.class).getResultList();
            trans.commit();
            return cust;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public Customer findCustomerById(long custId) {
        try{
            trans.begin();
            Customer cust= (Customer) em.createNativeQuery("select * from customers where custID= "+custId+"",Customer.class).getSingleResult();
            trans.commit();
            return cust;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
}

