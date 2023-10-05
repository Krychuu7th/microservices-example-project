package com.example.employeeservice.repository;

import com.example.employeeservice.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(Employee employee) {
        employees.add(employee);

        return employee;
    }

    public Employee findById(Long id) {
        return employees.stream().filter(employee -> id.equals(employee.id())).findFirst().orElseThrow();
    }

    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employees.stream().filter(employee -> departmentId.equals(employee.departmentId())).toList();
    }
}
