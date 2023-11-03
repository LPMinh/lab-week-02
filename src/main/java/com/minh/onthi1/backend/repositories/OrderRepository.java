package com.minh.onthi1.backend.repositories;


import com.minh.onthi1.backend.models.Customer;
import com.minh.onthi1.backend.models.Employee;
import com.minh.onthi1.backend.models.Order;
import com.minh.onthi1.backend.models.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private EntityManager em;
    private EntityTransaction trans;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private OrderDetailRepository orderDetailRepository;

    public OrderRepository() {
        em = Persistence
                .createEntityManagerFactory("lab_week_2")
                .createEntityManager();
        trans = em.getTransaction();
        customerRepository = new CustomerRepository();
        employeeRepository = new EmployeeRepository();
        orderDetailRepository = new OrderDetailRepository();
    }

    public Boolean insertOrder(Order order) {
        try {
            trans.begin();
            Customer cus = customerRepository.findCustomerById(order.getCustomer().getCustId());
            Employee employee = employeeRepository.findbyId(order.getEmployee().getEmp_id()).orElse(null);

            if (cus == null || employee == null) {
                return false;
            } else {
                // Set the Order for each OrderDetail
                for (OrderDetail orderDetail : order.getOrderDetails()) {
                    orderDetail.setOrder(order);
                }

                em.persist(order);
                trans.commit();
                return true;
            }
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
            return false;
        }
    }


    public boolean removeOrder(long orderID) {
        Order order = findOrderById(orderID).get();
        if (order == null) {
            return false;
        } else {
            try {
                trans.begin();
                em.remove(order);
                trans.commit();
                return true;
            } catch (Exception ex) {
                trans.rollback();
                logger.error(ex.getMessage());
                return false;
            }
        }
    }

    public boolean update(Order order) {
        try {
            trans.begin();
            em.merge(order);
            trans.commit();
            return true;
        } catch (Exception ex) {
            trans.rollback();
            logger.error(ex.getMessage());
            return false;
        }
    }

    public Optional<Order> findOrderById(long id) {
        try {
            trans.begin();
            String sql = "SELECT * FROM orders WHERE order_id = ?";
            Order order = (Order) em.createNativeQuery(sql, Order.class)
                    .setParameter(1, id)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            trans.commit();
            return order == null ? Optional.empty() : Optional.of(order);
        } catch (Exception ex) {
            trans.rollback();
            logger.error("Error while finding product by ID: " + ex.getMessage());
            return Optional.empty();
        }
    }

    public List<Order> getAllOrder() {
        return em.createNamedQuery("order.all", Order.class).getResultList();
    }
}
