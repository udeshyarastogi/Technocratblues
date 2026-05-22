package com.Assignment.Technocrat.service;

import com.Assignment.Technocrat.entity.Employee;
import com.Assignment.Technocrat.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ADD EMPLOYEE
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // GET ALL EMPLOYEES
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // UPDATE EMPLOYEE
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(updatedEmployee.getName());
        employee.setDepartment(updatedEmployee.getDepartment());
        employee.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(employee);
    }

    // DELETE EMPLOYEE
    public String deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
        return "Employee deleted successfully";
    }

    // SEARCH BY NAME
    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    // SEARCH BY DEPARTMENT
    public List<Employee> searchByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    // SORT BY ID
    public List<Employee> sortById() {
        return employeeRepository.findAll(Sort.by("employeeId"));
    }

    // SORT BY NAME
    public List<Employee> sortByName() {
        return employeeRepository.findAll(Sort.by("name"));
    }
}