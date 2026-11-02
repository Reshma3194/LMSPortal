package com.EmployeeLeave.LMSPortal.repository;

import com.EmployeeLeave.LMSPortal.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmpEmail(String email);
}