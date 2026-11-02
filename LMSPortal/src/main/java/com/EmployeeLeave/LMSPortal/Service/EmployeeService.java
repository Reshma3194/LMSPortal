package com.EmployeeLeave.LMSPortal.Service;

import com.EmployeeLeave.LMSPortal.model.Employee;
import com.EmployeeLeave.LMSPortal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee addEmployee(Employee employee) {
        if (employee.getEmpEmail() == null || employee.getEmpEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        return employeeRepository.save(employee);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Employee getEmployeeById(Integer empId) {
        Optional<Employee> employee = employeeRepository.findById(empId);
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found with ID: " + empId);
        }
        return employee.get();
    }
    
    public Employee updateEmployee(Integer empId, Employee employeeDetails) {
        Employee employee = getEmployeeById(empId);
        employee.setEmpName(employeeDetails.getEmpName());
        employee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(employee);
    }
    
    public void deleteEmployee(Integer empId) {
        Employee employee = getEmployeeById(empId);
        employeeRepository.delete(employee);
    }
}