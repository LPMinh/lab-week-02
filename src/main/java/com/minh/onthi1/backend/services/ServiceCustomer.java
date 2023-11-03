package com.minh.onthi1.backend.services;

import com.minh.onthi1.backend.models.Customer;
import com.minh.onthi1.backend.repositories.CustomerRepository;

import java.util.List;

public class ServiceCustomer {
    private CustomerRepository customerRepository;
    public ServiceCustomer(){
        customerRepository=new CustomerRepository();
    }
    public boolean addCustomer(Customer cus){
        return customerRepository.addCustomer(cus);
    }
    public boolean deleteCustomer (long id){
        return customerRepository.deleteCustomerByID(id);
    }
    public Customer updateCustomer(Customer cus){
        return customerRepository.updateCustomer(cus);
    }
    public List<Customer> findByQuery(String query){
        return customerRepository.findCustomerByNameOrID(query);
    }
    public List<Customer> getAllCustomer(){
        return customerRepository.getAllCustomer();
    }
    public Customer findCustomerByID(long id){
        return customerRepository.findCustomerById(id);
    }

}
