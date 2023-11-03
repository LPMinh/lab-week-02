package com.minh.onthi1.backend.repositories;


import com.minh.onthi1.backend.models.Employee;
import com.minh.onthi1.backend.models.EmployeeStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;

    public EmployeeRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
    }

    public boolean insertEmp(Employee employee) {
        try {
            trans.begin();
            em.persist(employee);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
        }
        return false;
    }

    public boolean setStatus(long id, EmployeeStatus status) {
       Employee emp=findbyId(id).orElse(null);
       if(emp==null){
           return false;
       }else{
           emp.setStatus(status);
           try{
               trans.begin();
               em.merge(emp);
               trans.commit();
               return true;
           }catch (Exception e){
               logger.error(e.getMessage());
               return false;
           }
       }
    }

    public boolean update(Employee employee) {
        try {
            trans.begin();
            em.merge(employee);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
            return false;
        }
    }

    public Optional<Employee> findbyId(long id) {
        try {
            trans.begin();
            String sql = "SELECT * FROM employees WHERE emp_id = ?";
            Employee employee = (Employee) em.createNativeQuery(sql, Employee.class)
                    .setParameter(1, id)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            trans.commit();
            return employee == null ? Optional.empty() : Optional.of(employee);
        } catch (Exception ex) {
            trans.rollback();
            logger.error("Error while finding product by ID: " + ex.getMessage());
            return Optional.empty();
        }
    }

    public List<Employee> getAllEmp() {
        try{
            trans.begin();
            List<Employee> list= em.createNativeQuery("select * from employees",Employee.class).getResultList();
            trans.commit();
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<Employee> getEmployeesByStatus(EmployeeStatus employeeStatus) {
        try{
            trans.begin();
            List<Employee> list=em.createNativeQuery("select * from employees where status= ?1",Employee.class).setParameter(1,employeeStatus.getCode()).getResultList();
            trans.commit();
            return list;
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }
    //...
}