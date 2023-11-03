package com.minh.onthi1.backend.services;

import com.minh.onthi1.backend.models.Employee;
import com.minh.onthi1.backend.models.EmployeeStatus;
import com.minh.onthi1.backend.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private EmployeeRepository employeeRepository=new EmployeeRepository();
    public EmployeeService(){
        employeeRepository=new EmployeeRepository();
    }
    public boolean addEmployee(Employee employee){
        return employeeRepository.insertEmp(employee);
    }
    public boolean updatEmployee(Employee employee){
        return employeeRepository.update(employee);
    }
    public Optional<Employee> findEmployeeByID(Long id){
        return employeeRepository.findbyId(id);
    }
    public List<Employee>  getAllEmployee(){
        return employeeRepository.getAllEmp();
    }
    public boolean updateStatus(EmployeeStatus status,Long empID){
        return employeeRepository.setStatus(empID,status);
    }
    public List<Employee>  getEmployeeByStatus(EmployeeStatus employeeStatus){
      return employeeRepository.getEmployeesByStatus(employeeStatus);
    }
}
