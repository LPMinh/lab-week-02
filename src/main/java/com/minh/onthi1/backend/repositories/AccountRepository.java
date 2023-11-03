package com.minh.onthi1.backend.repositories;

import com.minh.onthi1.backend.models.Account;
import com.minh.onthi1.backend.models.Customer;
import com.minh.onthi1.backend.models.Grant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AccountRepository {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;
    public AccountRepository(){
        em= Persistence.createEntityManagerFactory("lab_week_2").createEntityManager();
        trans= em.getTransaction();
    }
    public boolean addCAccount(Account cus){
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
    public boolean deleteAccoutByID(String id){
        try{
            trans.begin();
            Account  cus= new Account();
            cus.setAccountID(id);
            em.remove(cus);
            trans.commit();
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }

    public Account auththenticAccount(String email,String password){
        try{
            trans.begin();
            Account account= (Account) em.createNativeQuery("select * from account where email=?1 and password =?2", Account.class).setParameter(1,email).setParameter(2,password).getSingleResult();
            trans.commit();
            return account;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }
    public boolean authorizeAccount(String accountID){
        try{
            trans.begin();
            Grant grant = (Grant) em.createNativeQuery("select * from grant_access where account_id=?1 and role_id='admin'",Grant.class).setParameter(1,accountID).getSingleResult();
            trans.commit();
            boolean rs=(grant!=null)?true:false;
            return rs;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
}
