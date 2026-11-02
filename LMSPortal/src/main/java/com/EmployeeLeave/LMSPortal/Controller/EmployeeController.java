package com.EmployeeLeave.LMSPortal.Controller;

import com.EmployeeLeave.LMSPortal.model.Employee;
import com.EmployeeLeave.LMSPortal.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer empId) {
        Employee employee = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    @PutMapping("/{empId}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Integer empId,
            @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(empId, employeeDetails);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    
    @DeleteMapping("/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId) {
        employeeService.deleteEmployee(empId);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}